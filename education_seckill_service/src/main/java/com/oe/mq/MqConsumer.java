package com.oe.mq;

import com.alibaba.fastjson.JSONObject;

import com.oe.constant.SeckillRedis;
import com.oe.mapper.SeckillGoodsMapper;
import com.oe.mapper.SeckillOrderMapper;
import com.oe.pojo.TabSeckillGoods;
import com.oe.pojo.TabSeckillOrder;
import com.oe.util.IdWorker;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @Deacription TODO
 * @Author 石晗
 * @Date 2020/12/25 21:38
 * @Version 1.0
 **/
@Component
@RocketMQMessageListener(consumerGroup = "my-consumer-seckillCourse",topic = "seckillCourse")
public class MqConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    //RocketMQPushConsumerLifecycleListener,    关闭springboot的mq默认配置,手动接管ACK机制,当我们手动接管了之后,
    //onMessage 将失效,    执行prepareStart

    @Resource
    private SeckillOrderMapper seckillOrderMapper;
    @Resource
    private SeckillGoodsMapper seckillGoodsMapper;
    @Resource
    private IdWorker idWorker;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public void onMessage(String json) {   //只有这个方法是springboot的默认配置,若9/0错误,则默认16死信队列        可以手动接管ack机制
//        System.out.println("消费消息成功,拿到goodsId : " + payLog + "开始生成积分");
    }

    /**
     * 自定义消息消费重试策略（手动接管ACK确认机制）
     * CONSUME_SUCCESS、RECONSUME_LATER
     * 注意：保留原有消费者监听RocketMQListener
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                MessageExt msg = list.get(0);
                String msgId = msg.getMsgId();
                String msgbody = null;
                try {
                    msgbody = new String(msg.getBody(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println(msgbody + " 收到的消息为 : " + list);
                JSONObject jsonObject = JSONObject.parseObject(msgbody);
                String studentName = jsonObject.getObject("studentName", String.class);
                Long seckillId = jsonObject.getObject("seckillId", Long.class);
                Long transactionId = jsonObject.getObject("transactionId", Long.class);
                try {
                    //解决幂等性问题,  在我们消费之前要判断这条消息有没有消费成功,  如果消费成功后,    将消息id作为key保存到redis中
                    TabSeckillGoods seckillGoods = (TabSeckillGoods)redisTemplate.boundHashOps(SeckillRedis.KEY_SECKILLGOODS).get(seckillId);
                    //下单
                    TabSeckillOrder seckillOrder = new TabSeckillOrder();
                    seckillOrder.setStudentName(studentName);
                    seckillOrder.setTeacherName(seckillGoods.getTeacherName());
                    seckillOrder.setStatus("0");//代付款
                    seckillOrder.setCreateTime(new Date());
                    seckillOrder.setMoney(seckillGoods.getCostPrice());
                    seckillOrder.setId(idWorker.nextId());//snowflake
                    seckillOrder.setSeckillId(seckillId);
                    seckillOrder.setTransactionId(transactionId.toString());
                    //保存订单
                    seckillOrderMapper.insert(seckillOrder);
                    //将订单存入缓存
                    redisTemplate.boundHashOps(SeckillRedis.KEY_SECKILL_ORDER).put(studentName,seckillOrder);

                } catch (Exception e) {
                    System.out.println("消息失败--------------");
                    // 该条消息可以存储到DB或者LOG日志中，或其他处理方式，然后重试
                    //先获取消息的id, 消息的重复次数
                    System.out.println("消息的id为 : " + msg.getMsgId());
                    int times = msg.getReconsumeTimes();
                    System.out.println("消息的重试次数为 : " + times);
                    //消费了3次 = 第一次执行 + 重试了两次
                    if (times >= 2){
                        //整合redis,将消息保存到死信队列中,(存入redis死信列表),也可以保存到MySQL
                        TabSeckillOrder seckillOrder =  new TabSeckillOrder();
                        seckillOrder.setId(-999L);
                        redisTemplate.boundHashOps(SeckillRedis.KEY_SECKILL_ORDER).put(studentName,seckillOrder);
                        //还原库存      不存在库存
//                        redisTemplate.boundValueOps(SeckillRedis.KEY_SECKILLGOODS_STOCKCOUNT + seckillId).increment();
                        System.out.println("已经消费了3次,存入死信队列,再见,将数据还原,并通知前端订单生成失败");
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;// 重试
                }
                //返回成功之前将消息id保存到redis中
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

    }
}

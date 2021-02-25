package com.oe.mq;

import com.oe.service.SmsServiceI;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: springcloud
 * @description:
 * @author: 邢孟君
 * @create: 2021-02-02 16:30
 **/
@Component
// 配置消费者监听的topic和消费者的组
@RocketMQMessageListener(topic = "sms_phone_code", consumerGroup = "sms-group")
public class SendMessageMq implements RocketMQListener<String> {
    private static final Logger logger = LoggerFactory.getLogger(SendMessageMq.class);
    @Autowired
    private SmsServiceI smsServiceI;
    @Override
    public void onMessage(String strings) {

        String split[] = null;
        try {
            split = strings.split(",");
            smsServiceI.sendPhoneMessage(split[0],split[1]);
            logger.info("消息消费成功");
        } catch (Exception e) {
            logger.error("消费失败", e);
            e.printStackTrace();
        }
    }
}
    
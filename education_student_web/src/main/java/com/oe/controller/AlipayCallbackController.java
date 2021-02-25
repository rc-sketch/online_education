package com.oe.controller;

/**
 * @ProjectName: springcloud
 * @Package: com.oe.controller
 * @ClassName: AlipayCallbackController
 * @Author: Y眼中人Y
 * @Date: 2021/2/2 - 10:06
 * @Version: 1.0
 */

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.oe.config.AlipayConfig;
import com.oe.data.DataResult;
import com.oe.domain.vo.TabOrderVO;
import com.oe.domain.vo.TabPayLogVO;
import com.oe.domain.vo.TabStudentVO;
import com.oe.feign.yzh.OrderFeign;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.out;

/*
 * 支付宝支付回调类
 * */
@Controller
public class AlipayCallbackController {

//    @Resource
//    private ContentCategoryFeign contentCategoryFeign;
    @Resource
    private OrderFeign orderFeign;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    // 同步回调

    /**
     * @param out_trade_no 商户订单号
     * @param trade_no     支付宝交易号
     * @param total_amount 付款金额
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("returnUrl")
    public String returnUrl(Model model, HttpServletRequest request, String out_trade_no, String trade_no, String total_amount) throws AlipayApiException, UnsupportedEncodingException {
        //   http://339j856656.picp.vip/alipay/returnUrl?out_trade_no=1342312425373650945&trade_no=2020122522001412370501415891&total_amout=2369
        out.println(out_trade_no);
        out.println(trade_no);
        out.println(total_amount);
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        model.addAttribute("out_trade_no", out_trade_no);
        model.addAttribute("trade_no", trade_no);
        model.addAttribute("total_amount", total_amount);
        // 查看数字签名是否被更改
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        return "paysuccess";
    }

    /**
     * 异步回调
     * @param out_trade_no 商户订单号
     * @param trade_no     支付宝交易号
     * @param trade_status 交易状态
     * @param gmt_create   创建时间
     * @param total_amount 订单金额
     * @param gmt_payment  付款时间
     * @param request
     * @return
     */
    @RequestMapping("notifyUrl")
    @ResponseBody
    public String notifyUrl(String out_trade_no, String trade_no, String trade_status, String gmt_create, String total_amount, String gmt_payment, HttpServletRequest request) throws Exception {
        DataResult<List<TabOrderVO>> orderByAlipayId = orderFeign.findOrderByAlipayId(out_trade_no);
        TabOrderVO orderVO = orderByAlipayId.getData().get(0);
        if (getSignVerified(request)) {//验证成功
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 创建支付的日志
            TabPayLogVO tbPayLog = new TabPayLogVO();
            // 支付订单号
            tbPayLog.setOutTradeNo(out_trade_no);
            tbPayLog.setStudentId(orderVO.getStudentName());
            // 创建时间
            tbPayLog.setCreateTime(simpleDateFormat.parse(gmt_create));
            // 用户
            tbPayLog.setStudentId(orderVO.getStudentName());
            // 交易号码
            tbPayLog.setTransactionId(trade_no);
            // 订单编号列表
            tbPayLog.setOrderList(orderVO.getOrderId().toString());
            // 交易状态
            tbPayLog.setPayType("1");
            if (trade_status.equals("TRADE_CLOSED")) {
//                    // 未付款交易超时关闭，或支付完成后全额退款
//                    // 修改支付日志表
                    tbPayLog.setTotalFee(0L);
                    tbPayLog.setTradeState("0");
                    orderFeign.insertPayLog(tbPayLog);
//                    // 修改order表中的状态和数据 关闭交易
                List<TabOrderVO> data = orderByAlipayId.getData();
                 List<Long> ids = new ArrayList<>();
                data.forEach(list -> {
                     ids.add(list.getOrderId());
                });
                Long[] objects = (Long[]) ids.toArray();
                orderFeign.updateOrderStatusByOrderId(objects,"7");
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                // 修改支付日志表
                Number num = Float.parseFloat(total_amount); // double转lang
                 tbPayLog.setTotalFee(Long.valueOf(total_amount.substring(0, total_amount.length() - 3))); // 付款金额
                tbPayLog.setTotalFee(Long.valueOf(num.intValue())); // 付款金额  double转lang
                tbPayLog.setTradeState("1");
                tbPayLog.setPayTime(simpleDateFormat.parse(gmt_payment));
                orderFeign.insertPayLog(tbPayLog);
//                TabOrderDTO orderVO = new TabOrderDTO(out_trade_no,total_amount);
//                Message message = MessageBuilder.withPayload(orderVO).build();
                //事务消息发送者所在的组    事务消息发送的topic   要发送的消息
//                rocketMQTemplate.sendMessageInTransaction(ScoreConstant.TX_SCORE_INFO, ScoreConstant.TX_INTEGRAL_TOPIC, message, orderList);
                // 删除redis中的母订单
//                redisTemplate.boundHashOps(PayConstant.PAY_KEY).delete(orderList.get(0).getUserId() + out_trade_no);
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
            return "success";
        } else {//验证失败
            return "fail";
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
    }




    // 验证数字签名
    private boolean getSignVerified(HttpServletRequest request) throws Exception {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//             valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        return signVerified;
    }
}


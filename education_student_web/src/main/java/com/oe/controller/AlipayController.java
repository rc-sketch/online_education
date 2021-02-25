package com.oe.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ProjectName: dongdongshop_parent
 * @Package: com.yzh.controller
 * @ClassName: AlipayController
 * @Author: Y眼中人Y
 * @Date: 2020/12/24 - 15:08
 * @Version: 1.0
 */
@Controller
@RequestMapping("alipay")
public class AlipayController {

    private Logger logger = LoggerFactory.getLogger(AlipayController.class);
    @Resource
    private AlipayTradeQueryRequest alipayTradeQueryRequest;
    @Resource
    private AlipayClient alipayClient;

    @Resource
    private AlipayTradeRefundRequest alipayTradeRefundRequest;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private AlipayTradeFastpayRefundQueryRequest alipayTradeFastpayRefundQueryRequest;

    @Resource
    private AlipayTradePagePayRequest alipayTradePagePayRequest;

    /***
     * 调用支付
     * @param out_trade_no   订单号
     * @param body
     * @param total_amount  总金额
     * @param subject
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping("toPayInfo")
    @ResponseBody
    public String toPayInfo(String out_trade_no, String body, String total_amount, String subject) throws AlipayApiException {
        //设置请求参数
        alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
        //输出
        return result;
    }


    /**
     * 退款交易
     *
     * @param out_trade_no   商户订单号
     * @param trade_no       支付宝交易号
     * @param refund_amount  需要退款的金额
     * @param refund_reason  退款的原因说明
     * @param out_request_no out_request_no
     * @return
     */
    @RequestMapping("tradeRefund")
    public String tradeRefund(Model model, String out_trade_no, String trade_no, String refund_amount, String refund_reason, String out_request_no) {
        model.addAttribute("total_amount", refund_amount);
        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"trade_no\":\"" + trade_no + "\","
                + "\"refund_amount\":\"" + refund_amount + "\","
                + "\"refund_reason\":\"" + refund_reason + "\","
                + "\"out_request_no\":\"" + out_request_no + "\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        // 删除数据库中订单详细表(order_item)， 根据订单详细表的id去修改订单表(order)中的实际付款金额，状态，订单的更新时间
//            orderService.deleteItemOrderById(Long.parseLong(out_request_no), refund_amount);
//
////        // 更新这个订单对应的payLog
//              TbPayLog payLog = payServicep.selectbYOutTradeon(Long.parseLong(out_trade_no));
////        // 修改支付金额
//              payLog.setTotalFee(payLog.getTotalFee() - Long.parseLong(refund_amount));
//              payServicep.updatePayLog(payLog);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //输出
        return "trade_success";
    }

    @RequestMapping("tradeQuery")
    @ResponseBody
    public String tradeQuery(String out_trade_no,String trade_no) throws AlipayApiException {
        //商户订单号，商户网站订单系统中唯一订单号
        //支付宝交易号
        //请二选一设置
        alipayTradeQueryRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","+"\"trade_no\":\""+ trade_no +"\"}");

        //请求
        String result = alipayClient.execute(alipayTradeQueryRequest).getBody();
        return result;
    }

//    @RequestMapping("tradeRefund")
//    @ResponseBody
//    public String tradeRefund(String out_trade_no,String trade_no,String refund_amount,String refund_reason,String out_request_no) throws AlipayApiException {
//        //设置请求参数
////        //商户订单号，商户网站订单系统中唯一订单号
////        String out_trade_no = new String(request.getParameter("WIDTRout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
////        //支付宝交易号
////        String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"),"UTF-8");
////        //请二选一设置
////        //需要退款的金额，该金额不能大于订单金额，必填
////        String refund_amount = new String(request.getParameter("WIDTRrefund_amount").getBytes("ISO-8859-1"),"UTF-8");
////        //退款的原因说明
////        String refund_reason = new String(request.getParameter("WIDTRrefund_reason").getBytes("ISO-8859-1"),"UTF-8");
////        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
////        String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"),"UTF-8");
//
//        alipayTradeRefundRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
//                + "\"trade_no\":\""+ trade_no +"\","
//                + "\"refund_amount\":\""+ refund_amount +"\","
//                + "\"refund_reason\":\""+ refund_reason +"\","
//                + "\"out_request_no\":\""+ out_request_no +"\"}");
//
//        //请求
//        String result = alipayClient.execute(alipayTradeRefundRequest).getBody();
//        return result;
//    }


}
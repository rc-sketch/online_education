package com.oe.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.oe.constant.OssConstant;
import com.oe.service.SmsServiceI;
import org.springframework.stereotype.Service;

/**
 * @program: springcloud
 * @description:
 * @author: 邢孟君
 * @create: 2021-02-02 16:33
 **/
@Service
public class SmsServiceImpl implements SmsServiceI {
    @Override
    public void sendPhoneMessage(String phone, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", OssConstant.ACCESSKEYID, OssConstant.ACCESSKEYSECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        // String numeric = RandomStringUtils.randomNumeric(4);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");//发送的域名
        request.setSysVersion("2017-05-25");//版本
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "创想兵团");
        request.putQueryParameter("TemplateCode", "SMS_207530406");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}

    
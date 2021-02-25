package com.oe.config;

/**
 * @ProjectName: dongdongshop_parent
 * @Package: com.yzh.config
 * @ClassName: AlipayConfig
 * @Author: Y眼中人Y
 * @Date: 2020/12/24 - 0:53
 * @Version: 1.0
 */
public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    /*alipay*/

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000116696928";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCdBNWtUb5QdFC845bIa5fB1nXYbG3w7bTPN+1ln+i1eyFkKPaDBYtVFJ5MakFt0YQp016+rr9R3fQJWW8yXTHflc0s46gq+UqzUDZgha8KZyLsu2jqWAIHO+y3acZudDBitkACigym6Z3GnKQPshTfRFE5K/WvlGDNdvAUBwP0Ba1AI5biXyLMn/+KL3ttYC5r7RclORUJAceXB+10X0tlEYxKaU5WjVW3StLiWOi8boviOG5XhJLBGht7iOnpQezs7OZWX+YiAZn6F60B3vSO03RDfPWf2B5OxF10MjvF0GLvzo94n3cnEV+LZ0/y3VzRxMJfrpcbvsyIObznObVbAgMBAAECggEARbrP6ZhTnAcLk9a+L2MYkcxbqTD94fCuSxfbJTZOccmsmn5WevkLV01Av06wn8RbnPdxK90OuZyHs180MnmQnGo6I6U2GV97/QWyIEs94LDMobTYcrmCrRO/m/QtWv/ePPgSlQ4ZcEZu+rS1ZwL+oegE6g7LnJ2YH6aiF0LGTqdDPXgEwufAlK9BABtFxfe5WW3VujlboRoXnF4ALnjQRpSqHU5m1fEsxmDkXIqKF9uFGjr5NwKxqZJsqkxqMxCvjRzPXAYRyjsbZHhO/Bhw1TKv5Bf35B7GvXbWIP+TJGIkogyilkZEb0T8VM4fDQoDG03GEqyoKz8Zz1dlQc5/AQKBgQDjW7e0d9W+GlJQwp7PpslVPQtERk9bhrYBVM9JBsTmDXwtjBtrWoFojFpT/pi1rSEeVYj4OpDlcGodxpQzJO3+/QG/OKj8IvnQTEKm04yKyTosbj0lmQ5uJAQQ59IX9g/s3YxoDevwaZ+GCznYIGr1GlPIGafyEej81/sbxwVG6QKBgQCwzK26xefMB09SUjk4yHDbAKhFE/qPwkfl3huuT4wNPw1FiL/Y0DT/ZaWrhpe/6oVTvoFpAdNfPTrX4KUofJjeDaVg4ZJ7g+nC7Wmclvb2G2rK8J+wiTEswIQsQC/UrsXe4h2Fi0Y4cRUkkWVyhEIhlTwMbLRb7TOjXr9c7aW3owKBgHVR7LfJZphUSMm3w+9vJ1E+0MOIbIbUOrLxKo2KWqxDpSJzu+pnp4NleW8CX5bQIdjfVnCQ+BgNLBT1VaAWnGEJ+Y7NFBYXjkU4NOSVIV60e7pVoGP4WQPovdhjZ3xJ0untYPiIxokmV9T5kf2+rLKRxU3qP0mgiFOAt+kbh9ORAoGALSgqG3Fe8hLP5MPRj7pKcPVrBTHEJqHFwWWIhm4U5HE9T4xNC/CqCK2KshPPgJpC8Y/P9+UaetHlzKu5I20nklfD7MnQozGfLEV9WxyH+XYpQapR70IrSDXnzHGcheMZ8QKp7akUB2iCsPNeCPDU/0ThsH63Vl9weALEN9UPJacCgYBwpdOkg1CYGhCQeZtgW9p3drgKu96oc/ZS1kdapBptRBEeomvDR0/+YOI1YWAwnxk8J5tHXiXFdow/NUYc6iKWNiaeeQolU7S9GEsm5hsjzQI39nYQqYfpg4C6J5jBy+P4Zne0EFA5flszZnNbKMieVXwpzjtjPnJVKpyxDlfnEw==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl4ZQOUGNFeW8RUDVckoQ0/aaVbKMWREPvbQcHi6zn8nPyT90NFoTxhlyTHmUscbmFBNQEOw0qEO5Lkd1mPcifakaEHA7uhCW3iWsJaMbnLDMQfYgZvr/JULV2W8uKcb+OWNY6LjIsvgxkjSqahNZTddRX2gYV+mECsbhxXTqHUFazh4jFU30VB5l5ejqPN1eS5lbLlnTAKsqrwzNidcwrGR/T+uHlZ+SWjoJUx5EDzEs/NSop/35T/aVhQlM0kTMz+YTvbEPVS3PTPbWZeEgL15DQiCudsyMrvWa6OMHUFkXDn+DLjAvc1NA/gY73TVxEFfboZavMwplE+nOjCCzxQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://339q856i56.zicp.vip/notifyUrl";
    /*需要修改*/

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://339q856i56.zicp.vip/returnUrl";
    /*需要修改*/

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝日志地址
    public static String log_path = "D:\\idea\\log\\";

}

package com.oe.constant;

public interface OssConstant {
    String  ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    String  ACCESSKEYID = "LTAI4G4VtvhyjtMAZ7DUnRfa";
    String  ACCESSKEYSECRET= "qeRkS2TSQADkCwY3OdA2VFHkgVPLDA";
    String  BUCKETNAME= "dk2005-dongdongshop";
    // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
    String POINT =".oss-cn-beijing.aliyuncs.com/" ;
}

package com.oe.data;

//枚举类存存放的是一个一个对象，例如SUCCESS，BAD_REQUEST都是一个一个对象
//我们.哪个对象，哪个对象就代表当前类的一个对象使用
public enum ResponseStatusEnum {
    SUCCESS(200,"操作成功"),BAD_REQUEST(400,"参数异常"),INTERNAL_SERVER_ERROR(500,"INTERNAL_SERVER_ERROR")
    ,UNLOGIN(1001,"未登录");
    private Integer index;
    private String name;

    ResponseStatusEnum(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

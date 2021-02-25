package com.oe.data;

/**
 * @program: dongdongshop_parent
 * @description:
 * @author: zh
 * @create: 2020-12-09 19:51
 */
//统一返回值
public class DataResult<T> {

    private Integer code;
    private String message;
    private T data;
//返回一个枚举类对象，参数也是枚举类这个对象，使用者只需要传一个ResponseStatusEnum对象就可以
    public static DataResult response(com.oe.data.ResponseStatusEnum responseStatusEnum){
                                //使用枚举类定义的一个一个对象
        return new DataResult(responseStatusEnum.getIndex(),responseStatusEnum.getName());
    }
    public DataResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public DataResult() {
    }

    public DataResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public DataResult setData(T data) {
        this.data = data;
        return this;
    }
}
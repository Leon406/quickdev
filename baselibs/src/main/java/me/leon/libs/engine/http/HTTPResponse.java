package me.leon.libs.engine.http;

/**
 * Author:  Leon
 * Time:    2017/4/6 下午2:41
 * Desc:    网络请求数据类
 */

public class HTTPResponse<T> {

    private int code;
    private String msg;
    private T info;

    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public T getData() {
        return info;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}

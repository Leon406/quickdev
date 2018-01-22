package me.leon.libs.engine.http;

import java.io.Serializable;

/**
 * Author:  Parorisim
 * Time:    2017/4/6 下午2:41
 * Desc:    网络请求数据类
 */

public class SimpleResponse implements Serializable {

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    @Deprecated
    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }


    @Deprecated
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public HTTPResponse toHTTPResponse() {
        HTTPResponse httpResponse = new HTTPResponse();
        httpResponse.setCode(code);
        httpResponse.setMsg(msg);
        return httpResponse;
    }

}

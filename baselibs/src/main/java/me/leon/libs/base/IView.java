package me.leon.libs.base;

/**
 * Author:  Leon
 * Time:    2017/4/5 上午11:29
 */

public interface IView {

    /**
     * 发生错误
     * @param e 错误
     */
    void onError(Throwable e);
    /**
     * 连接失败
     * @param e 失败
     */
    void onFail(Throwable e);
}

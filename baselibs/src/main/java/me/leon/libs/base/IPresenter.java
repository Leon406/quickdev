package me.leon.libs.base;

/**
 * Author:  Leon
 * Time:    2017/4/5 上午11:31
 */

public interface IPresenter<V extends IView> {

    /**
     * 绑定 View
     *
     * @param view View
     */
    void attachView(V view);

    /**
     * 解绑 View
     */
    void detachView();

    /**
     * 获取 View
     * @return View
     */
    V getView();
}

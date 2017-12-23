package me.leon.quickdev.ui.activity.main;

import me.leon.libs.base.IPresenter;
import me.leon.libs.base.IView;

/**
 * Created by PC on 2017/12/23.
 */

class MainContract {

    interface View extends IView {
        void onFetchSuccess();

    }

    interface Presenter extends IPresenter<View> {
        void doFetch();
    }
}

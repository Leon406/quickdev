package me.leon.quickdev.ui.activity.main2;

import java.util.List;

import me.leon.libs.base.IPresenter;
import me.leon.libs.base.IView;
import me.leon.quickdev.bean.Meizi;

/**
 * Created by Leon on 2017/12/23 0023.
 */

public class Main2Contract {

    interface View extends IView{

        void onFetchSuccess(List<Meizi.Results> results);
    }

    interface Presenter extends IPresenter<View> {

        void doFetch(int page);
    }
}

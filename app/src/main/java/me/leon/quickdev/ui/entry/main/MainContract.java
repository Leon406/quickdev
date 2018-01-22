package me.leon.quickdev.ui.entry.main;

import java.io.File;
import java.util.List;

import me.leon.libs.base.IPresenter;
import me.leon.libs.base.IView;
import me.leon.quickdev.bean.SimpleUser;

/**
 * Created by PC on 2017/12/23.
 */

class MainContract {

    interface View extends IView {
        void onFetchSuccess(List<SimpleUser> user);

    }

    interface Presenter extends IPresenter<View> {
        void doFetch();

        void doDownload(String path);
        void doUpload(List<File> files);
    }
}

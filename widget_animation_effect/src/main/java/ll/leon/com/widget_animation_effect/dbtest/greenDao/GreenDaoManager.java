package ll.leon.com.widget_animation_effect.dbtest.greenDao;


import ll.leon.com.widget_animation_effect.App;
import ll.leon.com.widget_animation_effect.bean.greendao.DaoMaster;
import ll.leon.com.widget_animation_effect.bean.greendao.DaoSession;

/**
 * Author : Leon
 * E-mail : deadogone@gmail.com
 * Time   : 2017/8/2 0002 23:19
 * Desc   : This is GreenDaoManager
 * Version: 1.0.1
 */

public class GreenDaoManager {
    private static volatile GreenDaoManager mInstance = null;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private GreenDaoManager() {
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(App.ctx, "user.db");
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}

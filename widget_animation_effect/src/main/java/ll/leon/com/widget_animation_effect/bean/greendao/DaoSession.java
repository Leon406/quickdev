package ll.leon.com.widget_animation_effect.bean.greendao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

import ll.leon.com.widget_animation_effect.bean.Item;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig itemDaoConfig;

    private final ItemDao itemDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        itemDaoConfig = daoConfigMap.get(ItemDao.class).clone();
        itemDaoConfig.initIdentityScope(type);

        itemDao = new ItemDao(itemDaoConfig, this);

        registerDao(Item.class, itemDao);
    }
    
    public void clear() {
        itemDaoConfig.clearIdentityScope();
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

}
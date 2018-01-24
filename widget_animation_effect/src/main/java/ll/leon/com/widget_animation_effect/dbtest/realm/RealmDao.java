package ll.leon.com.widget_animation_effect.dbtest.realm;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmDao {

    public static Realm mRealm =Realm.getDefaultInstance();

    private RealmDao () {}


    public static void setmRealm(Realm mRealm) {
            RealmDao.mRealm = mRealm;
    }

    public static <T extends RealmObject> void insert(T obj) {
        mRealm.executeTransaction(realm -> realm.copyToRealm(obj));
    }
    public static <T extends RealmObject> void insertOrUpdate(T obj) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(obj));
    }
    public static <T extends RealmObject> void insertAll(List<T> lists) {
        mRealm.executeTransaction(realm -> realm.copyToRealm(lists));
    }
    public static <T extends RealmObject> void insertOrUpdateAll(List<T> lists) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(lists));
    }


    public static <T extends RealmObject> List<T> queryAll(Class<T> clazz) {
        return   toNormalList(mRealm.where(clazz).findAll());
    }
    public static <T extends RealmObject> T queryFirst(Class<T> clazz) {
      return   mRealm.where(clazz).findFirst();
    }

    public static <T extends RealmObject> RealmQuery<T> rawQuery(Class<T> clazz) {
        return   mRealm.where(clazz).greaterThan("id",50);
    }

    public static <T extends RealmObject> T queryLast(Class<T> clazz) {
        return   mRealm.where(clazz).findAll().last();
    }


    public static <T extends RealmObject> void deleteAll(Class<T> clazz) {
       mRealm.executeTransaction(realm -> realm.delete(clazz));
    }

    public static <T extends RealmObject> void deleteItem(T obj) {
        mRealm.executeTransaction(realm -> obj.deleteFromRealm());
    }
    public static <T extends RealmObject> void deleteResults(RealmResults<T> results) {
        mRealm.executeTransaction(realm -> results.deleteAllFromRealm());
    }


    public static <T extends RealmModel> List<T> toNormalList(RealmResults<T> results) {
        List<T> models = new ArrayList<>();
        for (T model : results) {
            models.add(model);
        }
        return models;
    }
}

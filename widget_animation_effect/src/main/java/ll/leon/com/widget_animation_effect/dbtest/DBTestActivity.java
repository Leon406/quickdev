package ll.leon.com.widget_animation_effect.dbtest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.zxing.BuildConfig;
import com.luck.picture.lib.permissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;
import ll.leon.com.widget_animation_effect.App;
import ll.leon.com.widget_animation_effect.R;
import ll.leon.com.widget_animation_effect.bean.Item;
import ll.leon.com.widget_animation_effect.bean.Model;
import ll.leon.com.widget_animation_effect.bean.greendao.ItemDao;
import ll.leon.com.widget_animation_effect.dbtest.greenDao.GreenDaoManager;
import ll.leon.com.widget_animation_effect.dbtest.realm.RealmDao;
import me.leon.libs.utils.T;

public class DBTestActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    private BaseQuickAdapter<Item, BaseViewHolder> adapter;
    private BaseQuickAdapter<Model, BaseViewHolder> modelAdapter;
    private ItemDao dao;
    private Random random = new Random(5555);
    ;
    private RxPermissions rxPermissions;

    public static void start(Context context) {
        Intent starter = new Intent(context, DBTestActivity.class);
        // starter.putExtra();
        context.startActivity(starter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);
        ButterKnife.bind(this);
        rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(granted -> {
                    if (granted) {
                        T.getInstance().show("已授权", T.OK);
                    }

                }

        );

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        dao = GreenDaoManager.getInstance().getNewSession().getItemDao();

        List<Item> data = new ArrayList<>();
        data.add(new Item(1, "DB"));


        adapter = new BaseQuickAdapter<Item, BaseViewHolder>(R.layout.item, data) {
            @Override
            protected void convert(BaseViewHolder helper, Item item) {

                helper.setText(R.id.btn, item.title)
                        .setOnClickListener(R.id.btn, v -> click(item, helper.getLayoutPosition()));

            }

            private void click(Item item, int position) {
                dao.deleteByKey(item._id);
                adapter.remove(position);

            }
        };

        modelAdapter = new BaseQuickAdapter<Model, BaseViewHolder>(R.layout.item) {
            @Override
            protected void convert(BaseViewHolder helper, Model item) {

                helper.setText(R.id.btn, item.title)
                        .setOnClickListener(R.id.btn, v -> click(item, helper.getLayoutPosition()));

            }

            private void click(Model item, int position) {
               // App.realm.executeTransaction(realm -> item.deleteFromRealm());
                RealmDao.deleteItem(item);
//                RealmResults<Model> all1 = App.realm.where(Model.class).findAll();
//                List<Model> models = transfrom2NormalList(App.realm.where(Model.class).findAll());

//                modelAdapter.setNewData(models);
                modelAdapter.remove(position);

            }
        };

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    private List<Model> transfrom2NormalList(RealmResults<Model> all1) {
        List<Model> models = new ArrayList<>();
        for (Model model : all1) {
            models.add(model);
        }

        return models;
    }

    @OnClick({R.id.sql, R.id.green, R.id.real, R.id.clear, R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sql:
                break;
            case R.id.green:

                int id = random.nextInt();
                Item entity = new Item(id, "test - " + id);
                dao.insertOrReplace(entity);
                adapter.setNewData(dao.loadAll());
                rv.setAdapter(adapter);
                break;
            case R.id.real:

                List<Model> list = new ArrayList<>();
                Model model = new Model();
                int id1 = random.nextInt(100);
                model.id = id1;
                model.title = "title" + id1;

                Model model2 = new Model();
                int id2 = random.nextInt(100);
                model2.id = id2;
                model2.title = "title" + id2;
                list.add(model);
                list.add(model2);
                RealmDao.insertAll(list);
//                App.realm.executeTransaction(realm -> realm.copyToRealm(model));
//                List<Model> models = transfrom2NormalList(App.realm.where(Model.class).findAll());

                modelAdapter.setNewData(RealmDao.queryAll(Model.class));
//                modelAdapter.setNewData(App.mRealm.where(Model.class).findAll());
                rv.setAdapter(modelAdapter);
                if (BuildConfig.DEBUG)
                    Log.d("DBTestActivity", "App.mRealm.where(Model.class).count():" + App.realm.where(Model.class).count());
                break;
            case R.id.clear:
                dao.deleteAll();
              //  App.realm.executeTransaction(realm -> realm.delete(Model.class));

                RealmDao.deleteAll(Model.class);
                adapter.setNewData(null);
                modelAdapter.setNewData(null);
                break;
            case R.id.query:

                modelAdapter.setNewData(
                        RealmDao.toNormalList(
                                RealmDao.rawQuery(Model.class)
                                        .findAll()));

                break;
        }
    }
}

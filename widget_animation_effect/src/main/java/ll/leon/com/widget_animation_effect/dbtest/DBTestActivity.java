package ll.leon.com.widget_animation_effect.dbtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ll.leon.com.widget_animation_effect.R;
import ll.leon.com.widget_animation_effect.bean.Item;
import ll.leon.com.widget_animation_effect.bean.greendao.ItemDao;
import ll.leon.com.widget_animation_effect.dbtest.greenDao.GreenDaoManager;

public class DBTestActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    private BaseQuickAdapter<Item, BaseViewHolder> adapter;
    private ItemDao dao;
    private Random random  = new Random(5555);;

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        dao = GreenDaoManager.getInstance().getNewSession().getItemDao();

        List<Item> data = new ArrayList<>();
        data.add(new Item(1, "DB"));
        data.add(new Item(2, "Http"));
        data.add(new Item(3, "Image"));
        data.add(new Item(4, "DB4"));
        data.add(new Item(5, "DB5"));
        data.add(new Item(6, "DB6"));


        adapter = new BaseQuickAdapter<Item, BaseViewHolder>(R.layout.item, data) {
            @Override
            protected void convert(BaseViewHolder helper, Item item) {

                helper.setText(R.id.btn, item.title)
                        .setOnClickListener(R.id.btn, v -> click(item,helper.getLayoutPosition()));

            }

            private void click(Item item,int position) {
                dao.deleteByKey( item._id);
                adapter.remove(position);

            }
        };

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    @OnClick({R.id.sql, R.id.green, R.id.real, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sql:
                break;
            case R.id.green:
                
                int id = random.nextInt();
                Item entity = new Item(id, "test - " + id);
                dao.insertOrReplace(entity);
                adapter.setNewData(dao.loadAll());
                break;
            case R.id.real:
                break;
            case R.id.clear:
                dao.deleteAll();
                adapter.setNewData(null);
                break;
        }
    }
}

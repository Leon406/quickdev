package ll.leon.com.widget_animation_effect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ll.leon.com.widget_animation_effect.bean.Item;
import ll.leon.com.widget_animation_effect.dbtest.DBTestActivity;
import ll.leon.com.widget_animation_effect.widget.SimpleToolbar;

public class FrameworkActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.simple_toolbar)
    SimpleToolbar simpleToolbar;

    public static void start(Context context) {
        Intent starter = new Intent(context, FrameworkActivity.class);
        //starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framework);
        ButterKnife.bind(this);

        simpleToolbar.setTitle("BaseFramework");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        List<Item> data = new ArrayList<>();
        data.add(new Item(1, "DB"));
        data.add(new Item(2, "Http"));
        data.add(new Item(3, "Image"));
        data.add(new Item(4, "DB4"));
        data.add(new Item(5, "DB5"));
        data.add(new Item(6, "DB6"));


        BaseQuickAdapter<Item, BaseViewHolder> adpter = new BaseQuickAdapter<Item, BaseViewHolder>(R.layout.item, data) {
            @Override
            protected void convert(BaseViewHolder helper, Item item) {

                helper.setText(R.id.btn, item.title)
                        .setOnClickListener(R.id.btn, v -> click(item._id));

            }

            private void click(long id) {
                switch ((int) id) {
                    case 1:
                        DBTestActivity.start(FrameworkActivity.this);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                }


            }
        };

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adpter);
    }
}

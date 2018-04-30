package ll.leon.com.widget_animation_effect;

import android.os.Bundle;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ll.leon.com.widget_animation_effect.helper.recyclerview.AbsAdapter;
import ll.leon.com.widget_animation_effect.widget.BannerRecyclerView;
import ll.leon.com.widget_animation_effect.widget.IndicatorView;

public class UIWidgetActivity extends RxAppCompatActivity implements BannerRecyclerView.OnBannerClickListener {

    @BindView(R.id.rv)
    BannerRecyclerView rv;
    @BindView(R.id.indicator)
    IndicatorView indicator;
    private boolean isAuto;
    private AbsAdapter<String> adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiwidget);
        ButterKnife.bind(this);

        list = new ArrayList<>();
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/69427561.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/23738150.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/30127126.jpg");
        list.add("http://oo6pz0u05.bkt.clouddn.com/17-12-13/36125492.jpg");

        rv.setInterval(1000)
                .setData(list)
                .setOnClickCallback(this)
                .startAutoPlay(true);

//        adapter = new AbsAdapter<String>(list, R.layout.item_image) {
//            @Override
//            protected void convert(BaseHolderHelper holder, String item, int position) {
//                L.getInstance().load(item, holder.getView(R.id.image));
//
//                holder.setOnClickListener(R.id.image, v -> Toast.makeText(UIWidgetActivity.this, "position:" + position, Toast.LENGTH_SHORT).show());
//
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//                position = position % list.size();
//                convert((BaseHolderHelper) holder, list.get(position), position);
//            }
//
//            @Override
//            public int getItemCount() {
//                return Integer.MAX_VALUE;
//            }
//        };
//        rv.setAdapter(adapter);

        indicator.setupWithRecyclerView(rv, list.size());

//        rv.scrollToPosition(list.size() * 20 - 1);
//        rv.smoothScrollToPosition(list.size() * 20);
//


    }

    @Override
    public void onBannerClickListener(int position) {
        Toast.makeText(this, "position:" + position, Toast.LENGTH_SHORT).show();
    }
}

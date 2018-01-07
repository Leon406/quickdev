package me.leon.quickdev.ui.entry.welcome;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import butterknife.BindView;
import butterknife.ButterKnife;
import me.leon.devsuit.android.SPUtils;
import me.leon.libs.base.BaseActivity;
import me.leon.libs.base.BasePresenter;

import me.leon.quickdev.R;
import me.leon.quickdev.ui.entry.main.MainActivity;
import me.leon.quickdev.widget.CircleIndicator;

/**
 * Created by Leon on 2018/1/6 0006.
 */

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;

    public static void start(Context context) {
        Intent starter = new Intent(context, WelcomeActivity.class);
       // starter.putExtra();
        context.startActivity(starter);
    }
    @Override
    protected int bindLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void onViewInit() {
        pager.setAdapter(new GuidePagerAdapter());
        indicator.setViewPager(pager);
        pager.setCurrentItem(0);
    }

    class GuidePagerAdapter extends PagerAdapter {
        private final int[] titles = {R.string.guide_tilte_1, R.string.guide_tilte_2, R.string.guide_tilte_3};
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_skip)
        TextView tvSkip;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.image)
        ImageView image;

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View guide = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_guide, container, false);
            ButterKnife.bind(this, guide);
            tvTitle.setText(titles[position]);
//            tvContent.setText(contents[position]);
//            image.setImageResource(images[position]);
            tvSkip.setOnClickListener(v -> {
                SPUtils.getInstance("setting").put("isShowWelcome",true);
                MainActivity.start(WelcomeActivity.this);
                finish();
            });
            tvSkip.setText(position == getCount() - 1 ?"进入" : "跳过");
            container.addView(guide);
            return guide;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}

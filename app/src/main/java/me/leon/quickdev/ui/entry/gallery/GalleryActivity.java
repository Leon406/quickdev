package me.leon.quickdev.ui.entry.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import java.util.ArrayList;

import butterknife.BindView;
import me.leon.libs.Config;
import me.leon.libs.base.BasePresenter;
import me.leon.libs.base.RightSwipeBaseActivity;
import me.leon.quickdev.R;
import me.leon.quickdev.widget.LightActionBar;

/**
 * Author:  Leon
 * Time:    2017/5/18 下午2:19
 * Desc:    图片预览容器
 */

public class GalleryActivity extends RightSwipeBaseActivity {


    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.actionbar)
    LightActionBar mActionBar;

    public static void start(Context context, int pos, ArrayList<String> url) {
        Intent starter = new Intent(context, GalleryActivity.class);
        starter.putExtra(Config.BUNDLE_POSITION, pos);
        starter.putExtra(Config.BUNDLE_DATA, url);
        context.startActivity(starter);
    }

    @Override
    protected int bindLayout() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_gallery;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void onViewInit() {

        int position = getIntent().getIntExtra(Config.BUNDLE_POSITION, 0);
        ArrayList<String> paths = getIntent().getStringArrayListExtra(Config.BUNDLE_DATA);

        mActionBar
                .reset()
                .setTitle(getString(R.string.title_gallery, position + 1, paths.size()))
                .setTransparent(true)
                .setLeftIcon(R.drawable.left)
                .addLeftIconAction(v -> onBackPressed());
        mActionBar.getLeftIconView().setColorFilter(Color.WHITE);
        mActionBar.getTitleView().setTextColor(Color.WHITE);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mActionBar.setTitle(getString(R.string.title_gallery, position + 1, paths.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), paths));
        mPager.setCurrentItem(position);
        handleTouchEventConflict(mPager);
    }


    private class PagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<String> mPaths;

        PagerAdapter(FragmentManager fm, ArrayList<String> paths) {
            super(fm);
            mPaths = paths;
        }

        @Override
        public Fragment getItem(int position) {
            return GalleryFragment.getNewInstance(mPaths.get(position));
        }

        @Override
        public int getCount() {
            return mPaths == null ? 0 : mPaths.size();
        }
    }
}

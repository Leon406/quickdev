package me.leon.quickdev.ui.entry.gallery;

import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import me.leon.libs.Config;
import me.leon.libs.base.BaseFragment;
import me.leon.libs.base.BasePresenter;
import me.leon.libs.engine.image.L;
import me.leon.quickdev.R;


/**
 * Author:  Leon
 * Time:    2017/5/18 下午2:19
 * Desc:    图片预览碎片
 */

public class GalleryFragment extends BaseFragment {

    @BindView(R.id.image)
    PhotoView image;

    public static GalleryFragment getNewInstance(String path) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Config.BUNDLE_DATA, path);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    protected int bindLayout() {
        return R.layout.fragment_gallery;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void onViewInit() {
        image.setOnPhotoTapListener((a, b, c) -> getActivity().finish());
        String path = getArguments().getString(Config.BUNDLE_DATA);
        L.getInstance().loadBigImage(path, image);

    }


}

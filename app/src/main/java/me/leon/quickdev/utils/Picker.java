package me.leon.quickdev.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Author:  Leon
 * Time:    2017/12/12 下午2:26
 * Desc:    图片选择器
 */

public class Picker {

    public static void OpenCamera(Activity activity) {
        if (!checkPermission(activity)) {
            return;
        }
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofImage())
//                .theme(R.style.picture_QQ_style)
                .maxSelectNum(1)
                .minSelectNum(1)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)

                .isCamera(true) //是否显示拍照
                .enableCrop(true)
//                .compress(true)
//                .glideOverride(160, 160)
//                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)
//                .hideBottomControls(cb_hide.isChecked() ? false : true)
//                .freeStyleCropEnabled(cb_styleCrop.isChecked())

//                .circleDimmedLayer(cb_crop_circular.isChecked())
//                .showCropFrame(cb_showCropFrame.isChecked())
//                .showCropGrid(cb_showCropGrid.isChecked())
//                .openClickSound(cb_voice.isChecked())
//                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);

    }

    /**
     * 打开相册并裁剪 （单图）
     *
     * @param fragment
     */
    public static void openGalleryWithCrop(Fragment fragment) {
        if (!checkPermission(fragment)) {
            return;
        }
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
//                .theme(R.style.picture_QQ_style)
                .maxSelectNum(1)
                .minSelectNum(0)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(true) //是否显示拍照
                .enableCrop(true)
                .hideBottomControls(true)
                .withAspectRatio(1, 1)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 打开相册并裁剪 （单图）
     *
     * @param fragment
     */
    public static void openGalleryWithCrop(Activity fragment, int requestCode) {
        if (!checkPermission(fragment)) {
            return;
        }
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
//                .theme(R.style.picture_QQ_style)
                .maxSelectNum(1)
                .minSelectNum(0)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(true) //是否显示拍照
                .enableCrop(true)
                .hideBottomControls(true)
                .withAspectRatio(1, 1)
                .forResult(requestCode);
    }

    /**
     * @param activity    单张图片 无裁剪
     * @param requestCode
     */
    public static void openGallery(Activity activity, int requestCode) {
        if (!checkPermission(activity)) {
            return;
        }
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
//                .theme(R.style.picture_QQ_style)
                .maxSelectNum(1)
                .minSelectNum(0)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(true) //是否显示拍照
                .forResult(requestCode);
    }

    /**
     * 打开相册 （多图 4 张）
     *
     * @param fragment
     * @param selectedList 选中图片  可传null
     */

    public static void openDynamicGallery(Fragment fragment, List<LocalMedia> selectedList) {
        if (!checkPermission(fragment)) {
            return;
        }
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
//                .theme(R.style.picture_QQ_style)
                .maxSelectNum(4) //按实际需求
                .minSelectNum(0)
                .selectionMode(PictureConfig.MULTIPLE)
                .previewImage(true)
                .isCamera(true) //是否显示拍照
                .selectionMedia(selectedList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 打开相册 （多图 4 张）
     *
     * @param activity
     * @param selectedList
     */

    public static Maybe<List<LocalMedia>> openDynamicGallery(Activity activity, List<LocalMedia> selectedList) {
        if (!checkPermission(activity)) {
            return null;
        }
        return PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
//                .theme(R.style.picture_QQ_style)
                .maxSelectNum(4) //按实际需求
                .minSelectNum(0)
                .compress(true)
                .selectionMode(PictureConfig.MULTIPLE)
                .previewImage(true)
                .isCamera(true) //是否显示拍照
                .selectionMedia(selectedList)
                .forActivityResult()
                .filter(result -> result.resultCode() == -1 && result.data() != null)

                //
                .flatMap(result -> Observable.fromIterable(
                        PictureSelector.obtainMultipleResult(result.data()))
                        // .map(image -> image.getPath())
                )
                .toList()
                .filter(images -> images.size() > 0);
    }

    public static void openDynamicGallery(Fragment fragment, int requestCode, List<LocalMedia> selectedList) {
        if (!checkPermission(fragment)) {
            return;
        }
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
//                .theme(R.style.picture_QQ_style)
                .maxSelectNum(4) //按实际需求
                .minSelectNum(0)
                .selectionMode(PictureConfig.MULTIPLE)
                .previewImage(true)
                .isCamera(true) //是否显示拍照
                .selectionMedia(selectedList)
                .forResult(requestCode);
    }

    /**
     * 打开相册并裁剪 （单图）
     *
     * @param fragment
     */
    public static void OpenCamera(Fragment fragment) {

        if (!checkPermission(fragment)) {
            return;
        }
        PictureSelector.create(fragment)
                .openCamera(PictureMimeType.ofImage())
//                .theme(R.style.picture_QQ_style)
                .maxSelectNum(1)
                .minSelectNum(1)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)

                .isCamera(true) //是否显示拍照
                .enableCrop(true)
//                .compress(true)
                .glideOverride(800, 800)
                .withAspectRatio(1, 1)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    private static boolean checkPermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(activity, "未授权", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 6662);
            return false;
        }

        return true;
    }

    private static boolean checkPermission(Fragment fragment) {
        if (ContextCompat.checkSelfPermission(fragment.getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(fragment.getActivity(), "未授权", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(fragment.getActivity(), new String[]{Manifest.permission.CAMERA}, 6662);
            return false;
        }

        return true;

    }


}

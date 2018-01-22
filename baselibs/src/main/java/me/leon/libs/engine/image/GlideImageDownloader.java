package me.leon.libs.engine.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bilibili.socialize.share.core.error.ShareException;
import com.bilibili.socialize.share.download.IImageDownloader;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

/**
 * Author:  Leon
 * Time:    2017/12/17 上午11:16
 * Desc:    Glide 图片下载器
 */

public class GlideImageDownloader implements IImageDownloader {

    @Override
    public void download(Context context, String imageUrl, String targetFileDirPath, OnImageDownloadListener listener) throws ShareException {


        GlideApp.with(context).load(imageUrl).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
                if (listener != null) listener.onStart();
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                if (listener != null) listener.onFailed(imageUrl);
            }

            @Override
            public void onResourceReady(File resource, Transition<? super File> transition) {
                if (listener != null) listener.onSuccess(resource.getPath());
            }
        });

    }
}

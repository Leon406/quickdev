package me.leon.libs.engine.image;


import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Leon on 2017/11/22 0022.
 */
@GlideModule
public class CustomGlide extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {


//       默认设置
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .skipMemoryCache(true);
        builder.setDefaultRequestOptions(options);
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);

//        builder.setBitmapPool(new LruBitmapPool(1024*1024*20));
        //      硬盘缓存大小
        int diskCacheSizeBytes = 1024 * 1024 * 400; // 默认250MB, 避免不同尺寸imageview重复同一url图片加载
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSizeBytes));
        builder.setDiskCache(new

                InternalCacheDiskCacheFactory(context, "", diskCacheSizeBytes));
    }
}

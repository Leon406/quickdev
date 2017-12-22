package me.leon.libs.engine.image;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;




/**
 * @author Leon
 * @dese ImageLoader
 */
public class L {

    private static final L INSTANCE = new L();
    private RequestManager mManager;

    private L() {
    }

    public static L getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        mManager = Glide.with(context);
    }

    public void load(String s, ImageView v) {

//        if (s != null && !s.startsWith("http") && s.contains("upload")) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(Config.API_IMAGE_BASE_URL).append(s);
//            s =sb.toString();
//        }

        GlideApp.with(v)
                .asDrawable()
                .centerCrop()
                .load(s)
                .override(v.getWidth() / 2, v.getHeight() / 2)
                .into(v);
    }

    public void loadBigImage(String s, ImageView v) {
        GlideApp.with(v)
                .load(s)
                .into(v);
    }

    public void loadCircle(String s, ImageView v) {

        GlideApp.with(v)
                .asDrawable()
                .circleCrop()
                .load(s)
                .override(v.getWidth() / 2, v.getHeight() / 2)
//                .placeholder(R.drawable.me_holder)
                .into(v);
    }

    public void loadCircle(@DrawableRes int s, ImageView v) {

        GlideApp.with(v)
                .asDrawable()
                .circleCrop()
                .load(s)
                .override(v.getWidth() / 2, v.getHeight() / 2)
                .into(v);
    }

    public void load(String s, ImageView ivImage, int resDefId) {

        GlideApp.with(ivImage.getContext())
                .asBitmap()
//                .asDrawable()
//        mManager
                .load(s)
                .centerCrop()
                .placeholder(resDefId)
                .override(ivImage.getWidth() / 2, ivImage.getHeight() / 2)

                .into(ivImage)
        ;

    }

    public void load(@DrawableRes int i, ImageView v) {
        GlideApp.with(v.getContext())

                .asDrawable()
                .centerCrop()
                .load(i).into(v);
    }
}

package me.leon.quickdev.ui.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.leon.libs.engine.image.L;
import me.leon.quickdev.R;
import me.leon.quickdev.bean.Meizi;

/**
 * Created by Leon on 2017/12/23 0023.
 */

public class MeiZiAdapter extends BaseQuickAdapter<Meizi.Results,BaseViewHolder> {
    public MeiZiAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Meizi.Results item) {

        L.getInstance().load(item.url,helper.getView(R.id.iv),R.drawable.default_share_image);
    }

}

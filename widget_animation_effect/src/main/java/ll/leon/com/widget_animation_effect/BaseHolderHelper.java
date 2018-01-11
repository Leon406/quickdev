package ll.leon.com.widget_animation_effect;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class BaseHolderHelper extends RecyclerView.ViewHolder {
    protected View itemView;

    public BaseHolderHelper(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public <T extends View> T getView(@IdRes int id) {

        return ((T) itemView.findViewById(id));
    }

    public BaseHolderHelper setText(@IdRes int id, String text) {
        TextView tv = getView(id);
        tv.setText(text);
        return this;
    }

    public BaseHolderHelper setText(@IdRes int id, @StringRes int strId) {
        TextView tv = getView(id);
        tv.setText(strId);
        return this;
    }

    public BaseHolderHelper setVisiable(@IdRes int id, boolean visiable) {
        getView(id).setVisibility(visiable ? View.VISIBLE : View.GONE);
        return  this;
    }

    public BaseHolderHelper setOnClickListener(@IdRes int id, boolean visiable) {
        getView(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return  this;
    }
}

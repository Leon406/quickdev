package ll.leon.com.widget_animation_effect.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.List;

import ll.leon.com.widget_animation_effect.R;
import ll.leon.com.widget_animation_effect.helper.recyclerview.AbsAdapter;
import ll.leon.com.widget_animation_effect.helper.recyclerview.BaseHolderHelper;
import me.leon.libs.engine.image.L;


public class BannerRecyclerView extends RecyclerView {

    private int interval = 3000;
    private Runnable runnable;
    private OnBannerClickListener listener;

    public BannerRecyclerView(Context context) {
        this(context, null);

    }

    public BannerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        setLayoutManager(layoutManager);
        new PagerSnapHelper().attachToRecyclerView(this);
        startAutoPlay(true);
    }

    public BannerRecyclerView startAutoPlay(boolean autoPlay) {
        if (autoPlay) {
            if (runnable == null) {
                runnable = new Runnable() {
                    @Override
                    public void run() {

                        if (getLayoutManager() instanceof LinearLayoutManager) {
                            int position = ((LinearLayoutManager) getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                            if (position != -1) {
                                smoothScrollToPosition(++position);
                            }
                            postDelayed(this, interval);
                        }
                    }
                };
            }
            postDelayed(runnable, interval);
        } else {
            getHandler().removeCallbacksAndMessages(null);
        }

        return this;
    }

    public BannerRecyclerView setData(List<String> list) {
        AbsAdapter adapter = new AbsAdapter<String>(list, R.layout.item_image) {
            @Override
            protected void convert(BaseHolderHelper holder, String item, int position) {
                L.getInstance().load(item, holder.getView(R.id.image));

                holder.setOnClickListener(R.id.image, v -> {
                            if (listener != null)
                                listener.onBannerClickListener(position);
                        }
                );

            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                position = position % list.size();
                convert((BaseHolderHelper) holder, list.get(position), position);
            }

            @Override
            public int getItemCount() {
                return Integer.MAX_VALUE;
            }
        };
        setAdapter(adapter);

        scrollToPosition(list.size() * 20 );
//       smoothScrollToPosition(list.size() * 20);

        return this;

    }

    public BannerRecyclerView setOnClickCallback(OnBannerClickListener listener) {
        this.listener = listener;
        return this;
    }

    public BannerRecyclerView setInterval(int interval) {
        this.interval = interval;
        return this;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                startAutoPlay(false);
                break;
            case MotionEvent.ACTION_MOVE:
                startAutoPlay(false);
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:

                startAutoPlay(true);
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoPlay(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        startAutoPlay(false);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);

        startAutoPlay(visibility == VISIBLE);
    }

    public interface OnBannerClickListener {

        void onBannerClickListener(int position);
    }

}

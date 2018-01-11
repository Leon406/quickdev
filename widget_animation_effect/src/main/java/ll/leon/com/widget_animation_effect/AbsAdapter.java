package ll.leon.com.widget_animation_effect;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class AbsAdapter<T> extends RecyclerView.Adapter {

    protected List<T> data;
    protected int resId;

    public AbsAdapter(List<T> data, @LayoutRes int resId) {
        this.data = data;
        this.resId = resId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
        return new BaseHolderHelper(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        convert((BaseHolderHelper) holder, data.get(position), position);
    }

    protected abstract void convert(BaseHolderHelper holder, T data, int position);

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void addData(T element) {
        data.add(element);
        notifyDataSetChanged();
    }
    public void addData(List<T> elements) {
        data.addAll(elements);
        notifyDataSetChanged();
    }
    public void setNewData(List<T> elements) {
        data.clear();
        data.addAll(elements);
        notifyDataSetChanged();
    }

    public void deleteData(T element) {
        data.remove(element);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        data.clear();
        notifyDataSetChanged();
    }
}

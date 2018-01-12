package ll.leon.com.widget_animation_effect;

import java.util.List;

import ll.leon.com.widget_animation_effect.helper.AbsAdapter;
import ll.leon.com.widget_animation_effect.helper.BaseHolderHelper;

public class TestAdapter extends AbsAdapter<String> {
    public TestAdapter(List<String> data, int resId) {
        super( data, resId);
    }

    @Override
    protected void convert(BaseHolderHelper holder, String data, int position) {
        holder.setText(android.R.id.text1,data);
    }


}

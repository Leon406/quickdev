package ll.leon.com.widget_animation_effect.widget;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import ll.leon.com.widget_animation_effect.R;


public class ConfirmDialog extends AbsDialog {

    public static ConfirmDialog newInstance(String tilte, String content) {

        Bundle args = new Bundle();

        ConfirmDialog fragment = new ConfirmDialog();
        args.putString("title", tilte);
        args.putString("content", content);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int bindLayout() {
        return R.layout.layout_dialog;
    }

    @Override
    protected void initDialog() {

        String title = getArguments().getString("title");
        String content = getArguments().getString("content");
        setText(R.id.tv, content).setText(R.id.tv_title, title);
        getView(R.id.tv).setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
            if (callback != null) {
                callback.confirm();
            }

        });

    }

    @Override
    protected void setAttribute(WindowManager.LayoutParams params) {
        params.height = screenHeight;
        params.width = screenWidth;
//        params.gravity = Gravity.BOTTOM;
    }

}

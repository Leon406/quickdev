package ll.leon.com.widget_animation_effect.widget;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import ll.leon.com.widget_animation_effect.R;


public abstract class AbsDialog extends AppCompatDialogFragment {


    protected int screenWidth;
    protected int screenHeight;
    protected float dpi;
    protected DialogCallback callback;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        dpi = getResources().getDisplayMetrics().density;
        Dialog dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.setContentView(bindLayout());
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    protected abstract @LayoutRes
    int bindLayout();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        setUpDialog();
    }

    public <T extends View> T getView(@IdRes int id) {
        return ((T) getDialog().findViewById(id));
    }

    public AbsDialog setText(@IdRes int id, String text) {
        TextView tv = getView(id);
        tv.setText(text);
        return this;
    }

    public AbsDialog setText(@IdRes int id, @StringRes int strId) {
        TextView tv = getView(id);
        tv.setText(strId);
        return this;
    }

    protected abstract void initDialog();


    protected void setUpDialog() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = (int) (screenWidth * 0.8 + 0.5);
            int height = -2;

            dialog.getWindow().setLayout(width, height);
            setDialogAttribute();
        }

    }

    protected void setDialogAttribute() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        setAttribute(params);
        window.setAttributes(params);

    }

    public void setDialogCallback(DialogCallback callback) {
        this.callback = callback;
    }

    protected void setAttribute(WindowManager.LayoutParams params) {
    }

    public interface DialogCallback {
        void confirm();

        void cancel();
    }

}

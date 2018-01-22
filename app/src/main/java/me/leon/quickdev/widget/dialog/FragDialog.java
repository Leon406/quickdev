package me.leon.quickdev.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment;

import me.leon.quickdev.R;

/**
 * Created by PC on 2018/1/2.
 */

public class FragDialog extends RxAppCompatDialogFragment {

    public static FragDialog newInstance() {

        Bundle args = new Bundle();

        FragDialog fragment = new FragDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_map_success, container, false);
//        view.findViewById(R.id.btn).setOnClickListener(v -> dismiss());
        return view;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        Dialog dialog = getDialog();
//        Window window = dialog.getWindow();
//        assert window != null;
////        window.setGravity(Gravity.BOTTOM);
////        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        DisplayMetrics metrics = getResources().getDisplayMetrics();
//        window.setLayout((int) (metrics.widthPixels - metrics.density * 16 + 0.5), (int) (metrics.heightPixels - metrics.density * 40 + 0.5));
//
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
////        layoutParams.flags |=
////                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN// 覆盖状态栏
//////                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL//窗口外可以点击
////                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE//不监听按键事件
//////                    | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
//////                    | WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN
////                        | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS//突破窗口限制
////                        | WindowManager.LayoutParams.FLAG_FULLSCREEN
////        ;
////        window.setAttributes(layoutParams);
//        window.setBackgroundDrawable(null);
//
//        dialog.setCancelable(false);
//
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

    }
}

package ll.leon.com.widget_animation_effect.widget.windows;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;

import ll.leon.com.widget_animation_effect.IndexActivity;
import ll.leon.com.widget_animation_effect.R;
import me.leon.devsuit.common.ShellUtils;

/**
 * @author Administrator
 * @date 2018/3/20
 */

public class HomeWindow extends AbsWindow {


    @Override
    public void show(Context application) {
        super.show(application);

//        view.setOnClickListener(this::onViewClicked);
//        view.setOnLongClickListener(this::onLongClicked);
    }

    private boolean onLongClicked(View view) {

//        LawMachineWebView.clear();


        ShellUtils.execCmd("pm clear com.yuanzong.lawmachine " +
                "|am force-stop com.yuanzong.lawmachin " +
                "|am start -n com.yuanzong.lawmachine/com.yuanzong.lawmachine.activity.WebViewActivity", true);


        return true;
    }

    @Override
    protected void initView() {
    }

    @Override
    public void customLayoutParameter() {
//        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        params.gravity = Gravity.TOP | Gravity.LEFT;
    }

    @Override
    public int bindLayout() {
        return R.layout.image;
    }

    private long lastClickTime = 0;//上次点击的时间

    private void onViewClicked(View view) {



        if (System.currentTimeMillis() - lastClickTime <= 2000) {//判断距离上次点击小于2秒

            return;

        }
        lastClickTime = System.currentTimeMillis();//记录这次点击时间
        if (listener != null) {
            listener.onClick();
        } else {
            back();
        }
    }


    public  void back() {

        Intent intent = new Intent(app, IndexActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        app.startActivity(intent);
    }
}



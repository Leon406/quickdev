package me.leon.quickdev;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import cn.jpush.android.api.JPushInterface;
import me.leon.quickdev.ui.activity.Main2Activity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

/**
 * Author:  Leon
 * Desc:    自定义推送接受器
 */

public class PushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent data) {
        // TODO: 2017/12/22  打开推送 进行相关操作
        if (data.getAction().equals(JPushInterface.ACTION_NOTIFICATION_OPENED)) {
            Intent intent = new Intent(context, Main2Activity.class);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent);
        }

        if (data.getAction().equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)) {
//            EventBus.getDefault().post(new NotificationEvent(true));
        }
    }
}

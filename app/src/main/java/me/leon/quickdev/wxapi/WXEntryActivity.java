package me.leon.quickdev.wxapi;

import com.bilibili.socialize.share.core.ui.BaseWXEntryActivity;
import me.leon.quickdev.Config;

/**
 * Author:  Parorisim
 * Time:    2017/5/17 上午11:11
 * Desc:    微信入口
 */

public class WXEntryActivity extends BaseWXEntryActivity {

    @Override
    protected String getAppId() {
        return Config.APP_ID_WECHAT;
    }
}

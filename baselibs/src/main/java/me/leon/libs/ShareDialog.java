package me.leon.libs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.bilibili.socialize.share.core.BiliShare;
import com.bilibili.socialize.share.core.SocializeListeners;
import com.bilibili.socialize.share.core.SocializeMedia;
import com.bilibili.socialize.share.core.shareparam.BaseShareParam;
import com.bilibili.socialize.share.util.ShareUtils;

import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment;

import me.leon.baselibs.R;
import me.leon.libs.utils.T;

/**
 * Author:  Parorisim
 * Time:    2017/5/17 下午3:14
 * Desc:    分享弹窗
 */

public class ShareDialog extends RxAppCompatDialogFragment {

    private static final int TO_WECHAT = 0;
    private static final int TO_DISCOVERY = 1;
    private static final int TO_QQ = 2;
    private static final int TO_QZONE = 3;

    public static ShareDialog getNewInstance(BaseShareParam data) {
        ShareDialog dialog = new ShareDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", data);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        assert window != null;
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_share, container, false);
        view.findViewById(R.id.tv_cancel).setOnClickListener(v -> dismiss());
        view.findViewById(R.id.ll_wechat).setOnClickListener(v -> doShare(TO_WECHAT));
        view.findViewById(R.id.ll_discovery).setOnClickListener(v -> doShare(TO_DISCOVERY));
        view.findViewById(R.id.ll_qq).setOnClickListener(v -> doShare(TO_QQ));
        view.findViewById(R.id.ll_qzone).setOnClickListener(v -> doShare(TO_QZONE));
        return view;
    }

    private void doShare(int to) {
        dismiss();
        SocializeMedia socializeMedia;
        switch (to) {
            case TO_WECHAT:
                socializeMedia = SocializeMedia.WEIXIN;
                if (!ShareUtils.isWeixinAvilible(getContext())) {
                    T.getInstance().show(getString(R.string.share_fail_uninstall,"微信"),T.ERR);
                    return;
                }
                break;
            case TO_DISCOVERY:
                socializeMedia = SocializeMedia.WEIXIN_MONMENT;
                if (!ShareUtils.isWeixinAvilible(getContext())) {
                    T.getInstance().show(getString(R.string.share_fail_uninstall,"微信"),T.ERR);
                    return;
                }
                break;
            case TO_QQ:
                socializeMedia = SocializeMedia.QQ;
                if (!ShareUtils.isQQClientAvailable(getContext())) {
                    T.getInstance().show(getString(R.string.share_fail_uninstall,"QQ"),T.ERR);
                    return;
                }
                break;
            case TO_QZONE:
                socializeMedia = SocializeMedia.QZONE;
                if (!ShareUtils.isQQClientAvailable(getContext())) {
                    T.getInstance().show(getString(R.string.share_fail_uninstall,"QQ"),T.ERR);
                    return;
                }
                break;
            default:
                socializeMedia = SocializeMedia.WEIXIN;
                break;
        }



        BiliShare.global().share((Activity) getContext(), socializeMedia, getArguments().getParcelable("data"), new SocializeListeners.ShareListener() {
            @Override
            public void onStart(SocializeMedia type) {

            }

            @Override
            public void onProgress(SocializeMedia type, String progressDesc) {

            }

            @Override
            public void onSuccess(SocializeMedia type, int code) {
                T.getInstance().show(R.string.share_success,T.OK);
            }

            @Override
            public void onError(SocializeMedia type, int code, Throwable error) {
                T.getInstance().show(R.string.share_fail,T.ERR);
            }

            @Override
            public void onCancel(SocializeMedia type) {
                T.getInstance().show(R.string.share_cancel,T.ERR);
            }
        });
    }

}

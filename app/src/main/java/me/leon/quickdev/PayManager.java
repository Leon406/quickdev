package me.leon.quickdev;

import android.app.Activity;


import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;



import io.reactivex.Flowable;
import me.leon.libs.Config;
import me.leon.libs.utils.RxUtils;

/**
 * Author:  Leon
 * Time:    2017/5/25 上午10:33
 * Desc:    支付管理类
 */

public class PayManager {

    private static final PayManager INSTANCE = new PayManager();
    private PayListener mListener;

    private PayManager() {
    }

    public static PayManager getInstance() {
        return INSTANCE;
    }

    public void startAliPay(Activity context, String params, PayListener listener) {

        mListener = listener;

        Flowable.fromCallable(()->new PayTask(context).payV2(params, false))
                .compose(RxUtils.rxSwitch())
                .subscribe(result -> {
                    int resultStatus = Integer.parseInt(result.get("resultStatus"));
                    if (resultStatus == 9000) {
                        mListener.onPaySuccess();
                    } else if (resultStatus == 6001) {
                        mListener.onPayFailure("支付被取消");
                    } else {
                        mListener.onPayFailure(result.get("memo"));
                    }
                });
    }

    public void startWeChatPay(Activity context, String appId, String partnerId, String prepayId, String packageValue, String nonceStr, String timeStamp, String sign, PayListener listener) {

        mListener = listener;

        IWXAPI iwxapi = WXAPIFactory.createWXAPI(context, null);
        iwxapi.registerApp(Config.APP_ID_WECHAT);

        if (!iwxapi.isWXAppInstalled()) {
            mListener.onPayFailure("微信未安装");
            return;
        }

        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = partnerId;
        request.prepayId = prepayId;
        request.packageValue = packageValue;
        request.nonceStr = nonceStr;
        request.timeStamp = timeStamp;
        request.sign = sign;

        iwxapi.sendReq(request);
    }

    public void onResp(BaseResp response) {
        if (response.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (response.errCode == 0) mListener.onPaySuccess();
            else if (response.errCode == -1) mListener.onPayFailure(response.errStr);
            else if (response.errCode == -2) mListener.onPayFailure("支付被取消");
            else mListener.onPayFailure(response.errStr);
        }
    }

    public interface PayListener {

        void onPayStart();

        void onPaySuccess();

        void onPayFailure(String message);
    }
}

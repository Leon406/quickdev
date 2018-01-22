package ll.leon.com.widget_animation_effect.zxing;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.common.BitmapUtils;
import com.luck.picture.lib.permissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import ll.leon.com.widget_animation_effect.BuildConfig;
import ll.leon.com.widget_animation_effect.R;
import me.leon.bankcardrecognize.BankCardScanActivity;
import me.leon.rxresult.RxActivityResult;

public class ZxingTestActivity extends AppCompatActivity {

    @BindView(R.id.code)
    ImageView code;
    @BindView(R.id.result)
    TextView tv;
    @BindView(R.id.tv_bank_card)
    TextView tvBank;
    private String cardId;
    private RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_test);
        ButterKnife.bind(this);
        rxPermissions = new RxPermissions(this);
    }

    public void scan(View view) {

        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        RxActivityResult.on(this)
                                .startIntent(new Intent(this, CaptureActivity.class))
                                .filter(result -> result.data() != null && result.resultCode() == 0)
                                .map(result -> result.data())
                                .subscribe(this::setImage);
                    } else {
                        Toast.makeText(this, "没有照相机权限", Toast.LENGTH_SHORT).show();
                    }

                });


    }

    private void setImage(Intent intent) {
        String result = intent.getStringExtra(CaptureActivity.SCAN_QRCODE_RESULT);
        Bitmap bitmap = intent.getParcelableExtra(CaptureActivity.SCAN_QRCODE_BITMAP);
        tv.setText(result);
        code.setImageBitmap(bitmap);
    }


    public void make(View view) {
        try {
            Bitmap bitmap = BitmapUtils.create2DCode("你好,Zxing");
            code.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void bankcard(View view) {
        Intent intentTack = new Intent(this, BankCardScanActivity.class);
        startActivityForResult(intentTack,888);

//       rxPermissions.request(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .subscribe(granted -> {
//                    if (granted) {
//                        RxActivityResult.on(this)
//                                .startIntent(new Intent(this, BankCardScanActivity.class))
//                                .filter(result -> result.data() != null && result.resultCode() == 0)
//                                .map(result -> result.data())
//                                .subscribe(this::setBankCardId, Throwable::printStackTrace);
//                    }
//                });
    }


    private void setBankCardId(Intent intent) {
        cardId = intent.getStringExtra(BankCardScanActivity.RESULT);
        if (BuildConfig.DEBUG) Log.d("ZxingTestActivity", cardId);
        tvBank.setText(cardId);
        Toast.makeText(this, cardId, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 888 && resultCode == 0) {
            setBankCardId(data);
//            String result = data.getStringExtra(BankCardScanActivity.RESULT);
//            tv.setText(result);
//            Toast.makeText(this, "result:" + result, Toast.LENGTH_SHORT).show();
        }
    }
}

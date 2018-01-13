package ll.leon.com.widget_animation_effect;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xujiaji.happybubble.BubbleDialog;
import com.xujiaji.happybubble.BubbleLayout;
import com.xujiaji.happybubble.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ll.leon.com.widget_animation_effect.widget.AbsDialog;
import ll.leon.com.widget_animation_effect.widget.ConfirmDialog;

public class DialogTestActivity extends AppCompatActivity {

    @BindView(R.id.dialog_3)
    Button bt3;
    private ConfirmDialog dialog;
    private View inflate;
    private BubbleDialog bubbleDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.dialog_1, R.id.dialog_2, R.id.dialog_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog_1:
                dialog = ConfirmDialog.newInstance("提醒", "注意ddd");
                dialog.show(getSupportFragmentManager(), "ConfirmDialog");
                dialog.setDialogCallback(new AbsDialog.DialogCallback() {
                    @Override
                    public void confirm() {
                        if (BuildConfig.DEBUG) Log.d("DialogTestActivity", "CallBack");
                    }

                    @Override
                    public void cancel() {

                    }
                });

                break;
            case R.id.dialog_2:
                Dialog dialog = new Dialog(this, R.style.dialog);
                dialog.setContentView(R.layout.layout_toolbar);

                dialog.show();
                break;
            case R.id.dialog_3:
                BubbleLayout bl = new BubbleLayout(this);
                bl.setBubbleColor(Color.BLUE);
                bl.setBubbleRadius(0);

                // bl.setShadowColor(Color.RED);
                bl.setLookLength(Util.dpToPx(this, 0));
                bl.setLookWidth(Util.dpToPx(this, 0));

                if (inflate == null) {
                    inflate = LayoutInflater.from(this).inflate(R.layout.layout_toolbar, null);
                    inflate.findViewById(R.id.txt_left_title).setOnClickListener(v -> Toast.makeText(this, "Haha", Toast.LENGTH_SHORT).show());
                    bubbleDialog = new BubbleDialog(this)
                            .addContentView(inflate)
                            .setClickedView(bt3)
                            .setPosition(BubbleDialog.Position.LEFT)
                            .setBubbleLayout(bl)
                            .setOffsetY(0)
                            .calBar(true);
                }

                bubbleDialog.show();
                break;

        }
    }
}

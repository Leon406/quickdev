package ll.leon.com.widget_animation_effect.immerse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ll.leon.com.widget_animation_effect.R;

public class IMActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immerse);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_fullscreen_notext, R.id.bt_fullscreen_text, R.id.bt_fragment_status, R.id.bt_toolbar_status_same_color, R.id.bt_change_statusbar_text_colr})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        Class clazz = null;
        switch (view.getId()) {
            case R.id.bt_fullscreen_notext:
                clazz = FullScrrenNoTextActivity.class;
                break;
            case R.id.bt_fullscreen_text:
                clazz = FullScrrenHaveTextActivity.class;
                break;
            case R.id.bt_toolbar_status_same_color:
                clazz = ToolBarAndStatusBarSameColorActivity.class;
                break;
            case R.id.bt_change_statusbar_text_colr:
                clazz = ChangeStatusBarColorActivity.class;
                break;
            case R.id.bt_fragment_status:
                clazz = FragmentStatusAndActionBarActivity.class;
                break;
        }
        intent.setClass(IMActivity.this, clazz);
        startActivity(intent);
    }

}

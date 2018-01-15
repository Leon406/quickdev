package ll.leon.com.widget_animation_effect;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ll.leon.com.widget_animation_effect.helper.StatusBarHelper;

public class AnimationActivity extends AppCompatActivity {

    @BindView(R.id.animateView)
    ImageView animateView;
    @BindView(R.id.animateView2)
    ImageView animateView2;
    @BindView(R.id.tv)
    TextView tv;
    private AlphaAnimation aa;
    private TranslateAnimation ta;
    private RotateAnimation ra;
    private ScaleAnimation sa;
    private AnimationSet animationSet;
    private ObjectAnimator rotationX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        StatusBarHelper.setDarkFontStatusBar(this);
        StatusBarHelper.setImmerse(this);

        initAnimation();

        initEnterExitAnimation();

    }

    private void initAnimation() {
        aa = new AlphaAnimation(1.0f, .5f);
        aa.setDuration(3000);
        aa.setRepeatMode(Animation.REVERSE);
        aa.setRepeatCount(-1);
        ta = new TranslateAnimation(0, -80, 0, -80);
        ta.setDuration(3000);
        ta.setRepeatMode(Animation.REVERSE);
        ta.setRepeatCount(-1);

        ra = new RotateAnimation(0, 360
                //,RotateAnimation.RELATIVE_TO_SELF, 0.5f,
               // RotateAnimation.RELATIVE_TO_SELF, 0.5f
        );
        ra.setDuration(1000);
        ra.setRepeatMode(Animation.REVERSE);
        ra.setRepeatCount(-1);

        sa = new ScaleAnimation(1, 1, 1, 0, ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(3000);
        sa.setRepeatMode(Animation.REVERSE);
        sa.setRepeatCount(-1);

        animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new BounceInterpolator());
        animationSet.addAnimation(ra);
        animationSet.addAnimation(aa);
        animationSet.addAnimation(sa);
        animationSet.addAnimation(ta);
    }

    private void initEnterExitAnimation() {

//        if (Build.VERSION.SDK_INT>=21) {
//            Slide slide = new Slide();
//            slide.setDuration(4000);
//            Fade fade = new Fade();
//            fade.setDuration(2000);
//
//            Explode explode = new Explode();
//            getWindow().setReenterTransition(explode);
//            getWindow().setExitTransition(fade);
//            getWindow().setReturnTransition(slide);
//            getWindow().setEnterTransition(fade);
//        }
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4,R.id.animateView,R.id.animateView2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:  //animation实现
                animateView.clearAnimation();
                animateView2.clearAnimation();

                animateView.startAnimation(ra);
                animateView2.startAnimation(animationSet);


                break;
            case R.id.bt2://animator实现
                animateView.clearAnimation();
                animateView2.clearAnimation();

                rotationX = ObjectAnimator.ofFloat(animateView, "rotationX", 0, 360);
                rotationX.setRepeatCount(-1);
                rotationX.setDuration(2000);
                rotationX.start();
                FloatEvaluator floatEvaluator = new FloatEvaluator();
                ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatedValue = (float) animation.getAnimatedValue();
                        animateView2.setRotationY(360*animatedValue);
                        animateView2.setAlpha(1-animatedValue);


                        Float evaluate = floatEvaluator.evaluate(animatedValue, 0, 100);
                        tv.setText(String.format("%.2f",evaluate));
                    }
                });

                animator.setDuration(2000);
//                animator.setInterpolator(new CycleInterpolator(6));
                animator.start();

                break;
            case R.id.bt3:  //View 实现
                animateView.clearAnimation();
                animateView2.clearAnimation();
                animateView.animate().alpha(0.3f)  //不能重复
                        .translationX(240)
                        .translationY(240)
                        .scaleX(3f)
                        .scaleY(3f)
                        .rotation(180)
                        .setDuration(10000)
                        .start();

                ViewCompat.animate(animateView2)
                        .rotationY(270)
                        .setDuration(6000)

                        .start();


                break;
            case R.id.bt4: //Activity Transition Animation
                ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, animateView, "share");

                startActivity(new Intent(this,ShareActivity.class),transitionActivityOptions.toBundle());
                overridePendingTransition(0,0);
//                if (Build.VERSION.SDK_INT>=21) {
//                    startActivity(new Intent(this,IndexActivity.class));
//                    overridePendingTransition(0,0);
//                }else {
//
//                }

                break;


            case R.id.animateView:

                Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
                break;
            case R.id.animateView2:
                Toast.makeText(this, "Hello2", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

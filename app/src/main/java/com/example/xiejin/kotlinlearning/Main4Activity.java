package com.example.xiejin.kotlinlearning;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;

import com.example.xiejin.kotlinlearning.widget.MyPathView4;

public class Main4Activity extends AppCompatActivity {
    private Button mButton;
    private ConstraintLayout mLayout;
    private MyPathView4 mView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mButton = findViewById(R.id.btn_fuck);
        mLayout = findViewById(R.id.wrap);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                loadAnimator();
//            }
//        }, 3000);
//        loadLayoutAnimation();
        mView4 = findViewById(R.id.mv4);
        mView4.startAnim();
    }

    private void loadLayoutAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.enter_in);
        animation.setDuration(3000);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);
        controller.setDelay(2000f);
        mLayout.setLayoutAnimation(controller);
        mLayout.startLayoutAnimation();
    }

    private void loadAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofInt(mButton, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mButton, "TranslationY", 0f, 300f);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1).after(animator);
        set.setInterpolator(new AccelerateInterpolator());
        set.setDuration(2000);
        set.start();
    }
}

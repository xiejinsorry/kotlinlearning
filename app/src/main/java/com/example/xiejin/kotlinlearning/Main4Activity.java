package com.example.xiejin.kotlinlearning;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.icu.text.Normalizer2;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.xiejin.kotlinlearning.widget.FlowLayout;
import com.example.xiejin.kotlinlearning.widget.MyPathView4;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    //    private Button mButton;
//    private ConstraintLayout mLayout;
//    private MyPathView4 mView4;
    private FlowLayout<String> mFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mFlowLayout = findViewById(R.id.flow_test);
        ArrayList<String> list = new ArrayList<>();
            list.add("fuckyouad");
        list.add("fuckyouadafdafa");
        list.add("fuckyo");
        list.add("fuckyouadffffffffff");
        list.add("fuckyouadqqqq");
        list.add("fuckyouaddasfafafdadgaag");
        list.add("fuckyouadqqqqq2");
        list.add("fuc2");
        list.add("fuckyouadqq");
        list.add("fuckyouadqqqqq2qqqqqqqqqqqqqqq");
        list.add("fuckyouadqqqqq2a");
        mFlowLayout.setData(list, new FlowLayout.FlowItemClickListener<String>() {
            @Override
            public void clickItem(String item) {
                Toast.makeText(Main4Activity.this, item, 0).show();
            }
        });
//        mButton = findViewById(R.id.btn_fuck);
//        mLayout = findViewById(R.id.wrap);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                loadAnimator();
//            }
//        }, 3000);
//        loadLayoutAnimation();
//        mView4 = findViewById(R.id.mv4);
//        mView4.startAnim();
    }

    public void changeColor(View v) {
        //改变纯色按钮的颜色
//        ImageView imageView = findViewById(R.id.iv_fuck);
//        imageView.setColorFilter(new PorterDuffColorFilter(0xff0000ff, PorterDuff.Mode.SRC_IN));
        /*设置滤镜对图像进行变化颜色
        LightingColorFilter filter = new LightingColorFilter(0xffffff, 0xf00000);
        ImageView imageView = findViewById(R.id.iv_fuck);
        imageView.setColorFilter(filter);
        */
//        ColorMatrix matrix = new ColorMatrix();
//        matrix.setSaturation(0);
//        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
//        imageView.setColorFilter(filter);
    }

    private void loadLayoutAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.enter_in);
        animation.setDuration(3000);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);
        controller.setDelay(2000f);
//        mLayout.setLayoutAnimation(controller);
//        mLayout.startLayoutAnimation();
    }

    private void loadAnimator() {
//        ObjectAnimator animator = ObjectAnimator.ofInt(mButton, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mButton, "TranslationY", 0f, 300f);
//        AnimatorSet set = new AnimatorSet();
//        set.play(animator1).after(animator);
//        set.setInterpolator(new AccelerateInterpolator());
//        set.setDuration(2000);
//        set.start();
    }
}

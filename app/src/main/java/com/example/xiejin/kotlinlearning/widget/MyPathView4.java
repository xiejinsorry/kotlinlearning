package com.example.xiejin.kotlinlearning.widget;

import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by xiejin on 2017/12/26.
 * 波长是400 波峰是100
 */

public class MyPathView4 extends View {
    private int mWaveLength = 1000;//一个波长400;波长包括波峰和波谷
    private int mWaveHeight = 100;//一个波峰50;
    private Paint mPaint;
    private int mOffsetX;
    private int mOffestY;

    public MyPathView4(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#00FF00"));
        mPaint.setStyle(Paint.Style.FILL);
    }

    public MyPathView4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(-mWaveLength + mOffsetX, 300+mOffestY);
        for (int i = -mWaveLength; i <= getWidth() + mWaveLength; i += mWaveLength) {
            path.rQuadTo(mWaveLength / 4, -mWaveHeight, mWaveLength / 2, 0);
            path.rQuadTo(mWaveLength / 4, mWaveHeight, mWaveLength / 2, 0);
        }
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();
        canvas.drawPath(path, mPaint);
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mWaveLength);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());//匀速
        animator.setDuration(2000);
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float flactor, Object start, Object end) {
                mOffsetX = (int) (mWaveLength * flactor);
                mOffestY = (int) (mWaveHeight * flactor);
                postInvalidate();
                return mOffsetX;
            }
        });
        animator.start();
    }
}

package com.example.xiejin.kotlinlearning.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiejin on 2017/12/26.
 * 波长是400 波峰是100
 */

public class MyPathView3 extends View {
    private Paint mPaint;
    public MyPathView3(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#666666"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
    }

    public MyPathView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(100, 300);
        path.rQuadTo(100, -100, 200, 0);
        path.rQuadTo(100, 100, 200, 0);
        canvas.drawPath(path, mPaint);
    }
}

package com.example.xiejin.kotlinlearning.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by xiejin on 2017/12/22.
 */

public class MyTextView extends View {
    private Paint mPaint;
    private String mText;

    public MyTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setTextSize(120);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setColor(Color.parseColor("#333333"));
    }

    public void setText(String str) {
        this.mText = str;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            width = Math.min(width, 1000);
        }
        setMeasuredDimension(width, height);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float left = 100;
        float top = 100;// 给定左上顶点
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        if (!TextUtils.isEmpty(mText)) {
            float baselineY = top - metrics.top;
            float width = mPaint.measureText(mText);
            canvas.drawLine(left, top, width, top, mPaint);//画top线
            canvas.drawText(mText, left, baselineY, mPaint);
            canvas.drawLine(left, baselineY, width, baselineY, mPaint);//画基线
            float bottom = metrics.bottom + baselineY;
            canvas.drawLine(left, bottom, width, bottom, mPaint);
        }
//        float baselineX = 0;
//        float baselineY = 200;
//        canvas.drawLine(baselineX, baselineY, 1000, baselineY, mPaint);
//        if (!TextUtils.isEmpty(mText)) {
//            canvas.drawText(mText, baselineX, baselineY, mPaint);
//            /**
//             * item1
//             */
//            Rect rect = new Rect();
//            mPaint.getTextBounds(mText, 0, mText.length(), rect);
//            int width = rect.width();
//            int height = rect.height();
//            Log.d("FUCKHUHU", "width:" + width + ",height:" + height);
//            /**
//             * item2
//             */
//            float w = mPaint.measureText(mText);
//            Log.d("FUCKHUHU", "w:" + w);
//        }

    }
}

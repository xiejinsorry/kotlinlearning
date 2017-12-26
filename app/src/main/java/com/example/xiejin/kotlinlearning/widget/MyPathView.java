package com.example.xiejin.kotlinlearning.widget;

import android.Manifest;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.xiejin.kotlinlearning.R;

/**
 * Created by xiejin on 2017/12/25.
 */

public class MyPathView extends View {
    private int mColor;
    private Path mPath;
    private Paint mPaint;


    public MyPathView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
    }

    public void setColor(int color) {
        this.mColor = color;
        mPaint.setColor(mColor);
    }

    public MyPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray tary = context.obtainStyledAttributes(attrs, R.styleable.MyPathView);
        mColor = tary.getColor(R.styleable.MyPathView_pathColor, Color.parseColor("#666666"));
        tary.recycle();
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = Math.min(widthSize, 300);
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = Math.min(heightSize, 300);
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                invalidate();
                break;
//            case MotionEvent.ACTION_UP:
//                mPath.reset();
//                break;
        }
        return super.onTouchEvent(event);
    }

    private void reset() {
        mPath.reset();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}

package com.example.xiejin.kotlinlearning.widget;

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
 * 每个点作为控制点，每个点之间的一般作为结束点
 */

public class MyPathView2 extends View {
    private int mColor;
    private Path mPath;
    private Paint mPaint;

    private float mControlX, mControlY;

    public MyPathView2(Context context) {
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

    public MyPathView2(Context context, @Nullable AttributeSet attrs) {
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
                mControlX = event.getX();
                mControlY = event.getY();
                mPath.moveTo(mControlX, mControlY);
                return true;
            case MotionEvent.ACTION_MOVE:
                float mEndX = (event.getX() + mControlX) / 2;
                float mEndY = (event.getY() + mControlY) / 2;
                mPath.quadTo(mControlX, mControlY, mEndX, mEndY);
                mControlX = event.getX();
                mControlY = event.getY();
                invalidate();
                break;
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

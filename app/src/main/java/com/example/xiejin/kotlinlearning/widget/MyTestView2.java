package com.example.xiejin.kotlinlearning.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.io.CharArrayReader;
import java.lang.ref.WeakReference;

/**
 * Created by xiejin on 2017/12/27.
 */

public class MyTestView2 extends AppCompatImageView {
    private WeakReference<Bitmap> mWeak, mWeakMask;
    private Paint mPaint;

    public MyTestView2(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setFilterBitmap(false);
    }

    public MyTestView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int radius = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(radius, radius);
    }

    //需要重置缓存里面的
    @Override
    public void setImageResource(int resId) {
        resetCache();
        super.setImageResource(resId);
    }

    public void resetCache() {
        this.mWeakMask = null;
        this.mWeak = null;
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        resetCache();
        super.setImageDrawable(drawable);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mWeak != null && mWeak.get() != null && !mWeak.get().isRecycled()) {
            canvas.drawBitmap(mWeak.get(), 0, 0, null);
        } else {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                int width = drawable.getIntrinsicWidth();
                int height = drawable.getIntrinsicHeight();
                Bitmap mSrcBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                Canvas mScrCanvas = new Canvas(mSrcBitmap);
                float scale = getWidth()*1.0f / Math.min(width, height);
                drawable.setBounds(0, 0, (int)(scale * width), (int)(scale * height));
                drawable.draw(mScrCanvas);
                Bitmap mMaskBitamp;
                if (mWeakMask == null || mWeakMask.get() == null || mWeak.get().isRecycled()) {
                    mMaskBitamp = getMaskBitmap();
                    mWeakMask = new WeakReference(mMaskBitamp);
                } else {
                    mMaskBitamp = mWeakMask.get();
                }
                mPaint.reset();
                mPaint.setFilterBitmap(false);
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                mScrCanvas.drawBitmap(mMaskBitamp, 0, 0, mPaint);
                mPaint.reset();
                mPaint.setFilterBitmap(false);
                canvas.drawBitmap(mSrcBitmap, 0, 0, null);
                mWeak = new WeakReference(mSrcBitmap);
            } else {
                super.onDraw(canvas);
            }
        }
    }

    public Bitmap getMaskBitmap() {
        Bitmap bm = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, mPaint);
        return bm;
    }
}

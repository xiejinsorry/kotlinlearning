package com.example.xiejin.kotlinlearning;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.PagerSnapHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by xiejin on 2017/12/5.
 */

public class MySrollImageView extends AppCompatImageView {
    private int mDy;
    private int mMinDy;

    public MySrollImageView(Context context) {
        super(context);
    }

    public MySrollImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(0, -mDy);//dy>0 上滑 坐标轴 方向是负
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getWidth() * drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth());
        }
        super.onDraw(canvas);
        canvas.restore();
    }

    /**
     * 前提是要 图片的高度 要小于 原图的高度
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMinDy = h;
        Log.d("fuck", "change2");
    }

    private Bitmap getDrawableToBitmap() {
        Drawable drawable = getDrawable();
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bm = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bm;
    }

    public void setDy(int dy) {
        this.mDy = dy - mMinDy;
        if (mDy < 0) {
            mDy = 0;
        }
        if (getDrawable() == null) return;
        if (mDy > getDrawable().getBounds().height() - mMinDy) {//最多只偏离这个距离不然会出现一部分显示空白
            mDy = getDrawable().getBounds().height() - mMinDy;
        }
        invalidate();
    }
}

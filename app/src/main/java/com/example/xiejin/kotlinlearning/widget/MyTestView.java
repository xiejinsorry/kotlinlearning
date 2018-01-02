package com.example.xiejin.kotlinlearning.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by xiejin on 2017/12/27.
 */

public class MyTestView extends AppCompatImageView {
    Paint mPaint;

    public MyTestView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setFilterBitmap(false);
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int radius = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(radius, radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            Bitmap mSrc = Bitmap.createBitmap(getWidth(), getWidth(), Bitmap.Config.ARGB_8888);
            Canvas canvasTarget = new Canvas(mSrc);
            float scale = getWidth() * 1.0F / Math.min(width, height);
            drawable.setBounds(new Rect(0, 0, (int) (width * scale), (int) (height * scale)));
            drawable.draw(canvasTarget);//画原图
            Bitmap bm = getMaskBitmap(getWidth(), getWidth());
            mPaint.reset();
            mPaint.setFilterBitmap(false);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvasTarget.drawBitmap(bm, 0, 0, mPaint);
            mPaint.setXfermode(null);
            canvas.drawBitmap(mSrc, 0, 0, null);
        }
    }

    public Bitmap getMaskBitmap(int width, int height) {
        Bitmap maskBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        Canvas canvas = new Canvas(maskBitmap);
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);
        return maskBitmap;
    }
}

package com.example.xiejin.kotlinlearning.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiejin.kotlinlearning.R;
import com.example.xiejin.kotlinlearning.entity.IFlowItem;
import com.example.xiejin.kotlinlearning.utils.ScreenUtil;

import java.util.ArrayList;

/**
 * Created by xiejin on 2017/12/28.
 */

public class FlowLayout<T> extends ViewGroup {
    private ArrayList<Integer> lineTopArray;
    public static final int TYPE_TXTVIEW = 1;
    public static final int TYPE_BUTTON = 2;
    private int mType;
    private int mTextColor;
    private int mItemMargin;
    private int mPadding;
    private int mBackGroundRes;
    private ArrayList<T> mDataList;
    private FlowItemClickListener<T> mListener;

    public FlowLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        lineTopArray = new ArrayList();
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        mTextColor = array.getColor(R.styleable.FlowLayout_textColor, Color.parseColor("#666666"));
        mPadding = (int) array.getDimension(R.styleable.FlowLayout_padding, ScreenUtil.dip2px(getContext(), 5));
        mBackGroundRes = array.getResourceId(R.styleable.FlowLayout_itemBg, R.color.colorPrimaryDark);
        mItemMargin = (int) array.getDimension(R.styleable.FlowLayout_itemMargin, ScreenUtil.dip2px(getContext(), 5));
        mType = array.getInt(R.styleable.FlowLayout_type, 1);
        array.recycle();
    }

    public interface FlowItemClickListener<T> {
        void clickItem(T item);
    }

    public void setData(ArrayList<T> data, FlowItemClickListener<T> listener) {
        this.mDataList = data;
        this.mListener = listener;
        addViews(mType);
    }

    private void addViews(int type) {
        switch (type) {
            case TYPE_BUTTON:
                createButtons();
                break;
            case TYPE_TXTVIEW:
                createTextViews();
                break;
            default:
                return;
        }
    }

    private void createTextViews() {
        for (final T t : mDataList) {
            if (!(t instanceof String || t instanceof IFlowItem)) {
                throw new IllegalArgumentException("can not identify this type");
            }
            TextView tv = new TextView(getContext());
            tv.setPadding(mPadding, mPadding, mPadding, mPadding);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(mBackGroundRes);
            tv.setTextColor(mTextColor);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.clickItem(t);
                }
            });
            if (t instanceof String) {
                tv.setText(t.toString());
            } else if (t instanceof IFlowItem) {
                tv.setText(((IFlowItem) t).getFlowShow());
            }
            MarginLayoutParams params = (MarginLayoutParams) generateDefaultLayoutParams();
            params.leftMargin = mItemMargin;
            params.topMargin = mItemMargin;
            addView(tv, params);
        }
    }

    private void createButtons() {
        for (final T t : mDataList) {
            if (!(t instanceof String || t instanceof IFlowItem)) {
                throw new IllegalArgumentException("can not identify this type");
            }
            Button btn = new Button(getContext());
            btn.setPadding(mPadding, mPadding, mPadding, mPadding);
            btn.setBackgroundResource(mBackGroundRes);
            btn.setGravity(Gravity.CENTER);
            btn.setTextColor(mTextColor);
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.clickItem(t);
                }
            });
            if (t instanceof String) {
                btn.setText(t.toString());
            } else if (t instanceof IFlowItem) {
                btn.setText(((IFlowItem) t).getFlowShow());
            }
            MarginLayoutParams params = (MarginLayoutParams) generateDefaultLayoutParams();
            params.leftMargin = mItemMargin;
            params.topMargin = mItemMargin;
            addView(btn, params);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int lineWidth = 0;
        int lineHeight = 0;
        int width = 0;
        int height = 0;
        lineTopArray.add(0);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            if (lineWidth + childWidth > widthSize) {//换行
                width = Math.max(width, lineWidth);
                lineWidth = childWidth;
                height += lineHeight;//增加上一行的height
                lineTopArray.add(height);
            } else {
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;
            }
        }
        //最后一行
        height += lineHeight;
        width = Math.max(width, lineWidth);
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : Math.min(width, widthSize), heightMode == MeasureSpec.EXACTLY ?
                heightSize : Math.min(height, heightSize));
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }

    @Override
    protected void onLayout(boolean b, int l, int i1, int i2, int i3) {
        int left = 0, right = 0, bottom = 0, top = 0;
        int index = 0;
        int lineWidth = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            if (lineWidth + childWidth > getMeasuredWidth()) {//换行
                left = params.leftMargin;//只有收个需要设置margin 因为后面的都包括在了childWidth和height里
                right = childWidth;//是加上了这left的leftMargin 也可以不加
                lineWidth = childWidth;
                index++;
            } else {
                left = params.leftMargin + right;//right 是包括前一个的margin  paramsleftMargin是当前的margin
                lineWidth += childWidth;
                right = lineWidth;
            }
            top = lineTopArray.get(index);
            top += params.topMargin;
            bottom = lineTopArray.get(index) + childHeight;
            child.layout(left, top, right, bottom);
        }
    }
}

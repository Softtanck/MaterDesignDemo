package com.softtanck.materdesigndemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/13/2015
 */
public class MyImageView extends ImageView {
    private Paint mPaints;
    private int mHeight;
    private int mWidth;

    private int mPadding = 20;

    private int mColor = 0x880000FF;
    private float current;
    private float mRatio;
    private float start = 0;
    private RectF rect;
    private ValueAnimator valueAnimator;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaints = new Paint();
        mPaints.setStyle(Paint.Style.STROKE);
        mPaints.setStrokeWidth(4);
        mPaints.setColor(mColor);
        mPaints.setAntiAlias(true);
        mRatio = current * 0.8f;
        valueAnimator = ValueAnimator.ofFloat(start, 360 + start).setDuration(360 * 10);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                current = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setRepeatCount(-1);
        valueAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        rect = new RectF(mPadding, mPadding, mHeight - mPadding, mWidth - mPadding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        if (current > mRatio) {
            start = current - mRatio;
        }
        canvas.drawArc(rect, start, current, false, mPaints);
    }
}

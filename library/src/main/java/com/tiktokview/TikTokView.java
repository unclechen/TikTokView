package com.tiktokview;

import com.tiktokview.utils.PxUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * TikTokView is a time TextView.
 * Created by nought on 2015/12/2.
 */
public class TikTokView extends View {

    private Context mContext;
    private Paint mPaint;
    private Rect mBounds;
    private TikTokMode mTikTokMode = TikTokMode.DECREMENT;
    private int mBackgroundColor;
    private float mPadding;
    private float mTextSize;
    private int mTextColor;
    private int mTikTokTintColor;
    private long mCurrentTime;
    private long mStartTime;
    private long mEndTime;

    public enum TikTokMode {
        INCREMENT,
        DECREMENT
    }

    public TikTokView(Context context) {
        super(context);
        init(context);
    }

    public TikTokView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TikTokView);
        mTextColor = typedArray.getColor(R.styleable.TikTokView_text_color, Color.BLACK);
        mBackgroundColor = typedArray.getColor(R.styleable.TikTokView_background_color, Color.TRANSPARENT);
        mTextSize = typedArray.getLayoutDimension(R.styleable.TikTokView_text_size, PxUtils.sp2px(mContext, 30));
        typedArray.recycle();
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        invalidate();
    }

    public long getCurrentTime() {
        return mCurrentTime;
    }

    public void setCurrentTime(long currentTime) {
        mCurrentTime = currentTime;
    }

    public float getPadding() {
        return mPadding;
    }

    public void setPadding(float padding) {
        mPadding = padding;
        invalidate();
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
        invalidate();
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float textSize) {
        mTextSize = textSize;
        invalidate();
    }

    public TikTokMode getTikTokMode() {
        return mTikTokMode;
    }

    public void setTikTokMode(TikTokMode tikTokMode) {
        mTikTokMode = tikTokMode;
    }

    public int getTikTokTintColor() {
        return mTikTokTintColor;
    }

    public void setTikTokTintColor(int tikTokTintColor) {
        mTikTokTintColor = tikTokTintColor;
        invalidate();
    }

    public long getStartTime() {
        return mStartTime;
    }

    public void setStartTime(long startTime) {
        mStartTime = startTime;
    }

    public long getEndTime() {
        return mEndTime;
    }

    public void setEndTime(long endTime) {
        mEndTime = endTime;
    }

    public void start() {

    }

    public void pause() {

    }

    public void stop() {

    }

    public void seekTo() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("TikTokView", "onDraw");
        super.onDraw(canvas);
        // draw background
        mPaint.setColor(mBackgroundColor);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        // draw text
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        String text;
        if (TikTokMode.DECREMENT == mTikTokMode) {
            text = mEndTime + "";
        } else {
            text = mStartTime + "";
        }
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth(); // MeasureSpec.getSize(measureSpec);
        int height = getMeasuredHeight();
        Log.d("TikTok", "onMeasure width = " + width);
        Log.d("TikTok", "onMeasure height = " + height);
        int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
        int heightWithoutPadding = height - getPaddingTop() - getPaddingBottom();
        Log.d("TT", "padding left = " + getPaddingLeft());
        Log.d("TikTok", "getDesiredWidth = " + getDesiredWidth());
        Log.d("TikTok", "getDesiredHeight = " + getDesiredHeight());
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                width = (int) getDesiredWidth();
                height = (int) getDesiredHeight();
                break;
            case MeasureSpec.AT_MOST:
                width = Math.min((int) getDesiredWidth(), width);
                height = Math.min((int) getDesiredHeight(), height);
                break;
            case MeasureSpec.EXACTLY:
                break;
        }

        setMeasuredDimension(width, height);
    }

    private float getDesiredWidth() {
        String text;
        if (TikTokMode.DECREMENT == mTikTokMode) {
            text = mEndTime + "";
        } else {
            text = mStartTime + "";
        }
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        return textWidth;
    }

    private float getDesiredHeight() {
        String text;
        if (TikTokMode.DECREMENT == mTikTokMode) {
            text = mEndTime + "";
        } else {
            text = mStartTime + "";
        }
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        return textHeight;
    }

}

package com.tiktokview;

import com.tiktokview.utils.PxUtils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
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
        mTextSize = (int) typedArray.getDimension(R.styleable.TikTokView_text_size, PxUtils.sp2px(mContext, 14));
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
        super.onDraw(canvas);
        // Draw the background.
        mPaint.setColor(mBackgroundColor);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        // Draw the numbers.
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        String text = getDesiredText();
        // Make the text align center.
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        mPaint.setTextAlign(Paint.Align.CENTER);
        int baseline = (getHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(text, getWidth() / 2, baseline, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getWidthMeasurement(widthMeasureSpec), getHeightMeasurement(heightMeasureSpec));
    }

    private int getWidthMeasurement(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int specSizeWithoutPadding = specSize - getPaddingLeft() - getPaddingRight();
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                specSizeWithoutPadding = (int) getDesiredWidth();
                break;
            case MeasureSpec.AT_MOST:
                specSizeWithoutPadding = Math.min((int) getDesiredWidth(), specSizeWithoutPadding);
                break;
            case MeasureSpec.EXACTLY:
                break;
        }
        return specSizeWithoutPadding + getPaddingLeft() + getPaddingRight();
    }

    private int getHeightMeasurement(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int specSizeWithoutPadding = specSize - getPaddingTop() - getPaddingBottom();
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                specSizeWithoutPadding = (int) getDesiredHeight();
                break;
            case MeasureSpec.AT_MOST:
                specSizeWithoutPadding = Math.min((int) getDesiredHeight(), specSizeWithoutPadding);
                break;
            case MeasureSpec.EXACTLY:
                break;
        }

        return specSizeWithoutPadding + getPaddingTop() + getPaddingBottom();
    }

    private float getDesiredWidth() {
        mPaint.setTextSize(mTextSize);
        String text = getDesiredText();
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        return mBounds.width();
    }

    private float getDesiredHeight() {
        mPaint.setTextSize(mTextSize);
        String text = getDesiredText();
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        return mBounds.height();
    }

    private String getDesiredText() {
        if (TikTokMode.DECREMENT == mTikTokMode) {
            return mEndTime + "";
        } else {
            return mStartTime + "";
        }
    }

}

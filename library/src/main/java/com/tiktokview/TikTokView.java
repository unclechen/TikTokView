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
    private String mTextFormat = "%d";
    private TikTokMode mTikTokMode = TikTokMode.DECREMENT;
    private String mBackgroundColor;
    private float mPadding;
    private float mTextSize;
    private int mTextColor;
    private int mTikTokTintColor;
    private long mCurrentTime;
    private long mTotalTime;

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
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TikTokView);
        mTextColor = typedArray.getColor(R.styleable.TikTokView_text_color, Color.BLACK);
        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
    }

    public String getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
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

    public String getTextFormat() {
        return mTextFormat;
    }

    public void setTextFormat(String textFormat) {
        mTextFormat = textFormat;
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

    public long getTotalTime() {
        return mTotalTime;
    }

    public void setTotalTime(long totalTime) {
        mTotalTime = totalTime;
    }

    public void start() {

    }

    public void pause() {

    }

    public void stop() {

    }

    public void seekTo() {

    }

    // 深入了解一下canvas都可以做什么
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(PxUtils.sp2px(mContext, 22));
        mPaint.getTextBounds(mTextFormat, 0, mTextFormat.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        canvas.drawText(mTextFormat, getWidth() / 2 - textWidth / 2,
                getHeight() / 2 + textHeight / 2, mPaint);
    }

}

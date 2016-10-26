package com.neworin.bordertextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * <b>Project:</b> NewOrinAndroidDemoCode<br>
 * <b>Create Date:</b> 2016/10/25<br>
 * <b>Author:</b> NewOrin<br>
 * <b>Description:</b>
 */

public class BorderTextView extends View {

    private static final String TAG = "BorderTextView";
    private TypedArray mTypedArray;
    private Paint mTextPaint;
    private Paint mBorderPaint;
    private Rect mRect;

    private String mText;
    private int mTextColor;
    private int mTextSize;
    private int mBorderWidth;
    private int mBorderBackgroundColor;

    private static final int BORDER_DEFAULT_WIDTH = 5;

    public BorderTextView(Context context) {
        this(context, null);
    }

    public BorderTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTypedArray =
                context.getTheme().obtainStyledAttributes(attrs, R.styleable.BorderTextView, defStyleAttr, 0);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        int n = mTypedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = mTypedArray.getIndex(i);
            switch (attr) {
                case R.styleable.BorderTextView_text:
                    mText = mTypedArray.getString(attr);
                    break;
                case R.styleable.BorderTextView_textColor:
                    //default color : black
                    mTextColor = mTypedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.BorderTextView_textSize:
                    mTextSize = mTypedArray.getDimensionPixelSize(attr,
                                                                  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                                                                                                  16,
                                                                                                  getResources()
                                                                                                          .getDisplayMetrics()));
                    break;
                case R.styleable.BorderTextView_borderWidth:
                    mBorderWidth = mTypedArray.getDimensionPixelSize(attr,
                                                                     (int) TypedValue.applyDimension(
                                                                             TypedValue.COMPLEX_UNIT_SP,
                                                                             BORDER_DEFAULT_WIDTH,
                                                                             getResources()
                                                                                     .getDisplayMetrics()));
                    break;
                case R.styleable.BorderTextView_borderBackgroundColor:
                    mBorderBackgroundColor = mTypedArray.getColor(attr, Color.BLUE);
                    break;
            }
        }
        mTypedArray.recycle();

        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setAntiAlias(false);

        mBorderPaint = new Paint();
        mBorderPaint.setColor(mBorderBackgroundColor);
        mBorderPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBorderPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setAntiAlias(false);

        mRect = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), mRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float textWidth = mRect.width();
        float textHeight = mRect.height();

        Log.d(TAG, "屏幕中心:(" + getMeasuredWidth() / 2 + "," + getMeasuredHeight() / 2 + ")");
        Log.d(TAG, "字体宽度" + textWidth);
        Log.d(TAG, "字体高度:" + textHeight);
        float textPosX = getMeasuredWidth() / 2 - textWidth / 2;
        float textPosY = getMeasuredHeight() / 2 + textHeight / 2;

        float circlePosX = getMeasuredWidth() / 2;
        float circlePosY = getMeasuredHeight() / 2;

        float radius = (textWidth > textHeight ? textWidth : textHeight) / 2;

        Log.d(TAG, "文字坐标点 : (" + textPosX + "," + textPosY + ")");
        Log.d(TAG, "圆的坐标点 : (" + circlePosX + "," + circlePosY + ")---圆半径:" + radius);
        canvas.drawCircle(circlePosX, circlePosY, radius, mBorderPaint);
        canvas.drawText(mText, textPosX, textPosY, mTextPaint);
    }
}

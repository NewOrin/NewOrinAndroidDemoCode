package com.neworin.bordertextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

    private int textWH;
    private int mWidth;
    private int mHeight;

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
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.setBackgroundColor(Color.GRAY);
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 获得宽高
         */
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mTextPaint.setTextSize(mTextSize);
            mTextPaint.getTextBounds(mText, 0, mText.length(), mRect);
            float textWidth = mRect.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            mWidth = desired;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mTextPaint.setTextSize(mTextSize);
            mTextPaint.getTextBounds(mText, 0, mText.length(), mRect);
            float textHeight = mRect.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            mHeight = desired;
        }
        textWH = (int) Math.sqrt((mHeight * mHeight + mWidth * mWidth));
        setMeasuredDimension(textWH, textWH);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d(TAG, "屏幕中心:(" + getMeasuredWidth() / 2 + "," + getMeasuredHeight() / 2 + ")");
        Log.d(TAG, "字体宽度" + mWidth);
        Log.d(TAG, "字体高度:" + mHeight);
        float circlePosX = getMeasuredWidth() / 2;
        float circlePosY = getMeasuredHeight() / 2;

        int radius = textWH / 2;
        Log.d(TAG, "圆的坐标点 : (" + circlePosX + "," + circlePosY + ")---圆半径:" + radius);
        canvas.drawCircle(circlePosX, circlePosY, radius, mBorderPaint);
        canvas.drawText(mText, circlePosX, circlePosY, mTextPaint);
    }
}

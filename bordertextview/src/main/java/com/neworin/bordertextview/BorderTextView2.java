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
import android.widget.TextView;

/**
 * <b>Project:</b> NewOrinAndroidDemoCode<br>
 * <b>Create Date:</b> 2016/10/25<br>
 * <b>Author:</b> NewOrin<br>
 * <b>Description:</b>
 */

public class BorderTextView2 extends TextView {

    private static final String TAG = "BorderTextView";
    private Paint mBorderPaint;
    private static float RADIUS = 100;

    public BorderTextView2(Context context) {
        this(context, null);
    }

    public BorderTextView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderTextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBorderPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setColor(Color.RED);
        mBorderPaint.setAntiAlias(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float centerX = getMeasuredWidth() / 2;
        float centerY = getMeasuredHeight() / 2;
        canvas.drawCircle(centerX, centerY, RADIUS, mBorderPaint);
        super.onDraw(canvas);
    }
}

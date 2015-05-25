package com.ht.zihuikongjian.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by annuo on 2015/5/25.
 */
public class ShanXingView extends View {

    private Paint arcPaint;

    public ShanXingView(Context context) {
        super(context);
        init(context, null);
    }

    public ShanXingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        arcPaint = new Paint();
        arcPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        arcPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        RectF rectF = new RectF(100, 100, 400, 400);
        canvas.drawArc(rectF, 45, 90, true, arcPaint);
        arcPaint.setColor(Color.RED);
        canvas.drawArc(rectF, 135, 90, true, arcPaint);
        arcPaint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, 225, 90, true, arcPaint);
        arcPaint.setColor(Color.GREEN);
        canvas.drawArc(rectF, 315, 90, true, arcPaint);
        arcPaint.setColor(Color.WHITE);
        canvas.drawCircle(250, 250, 40, arcPaint);

        //画一个椭圆吧
        RectF rectF1 = new RectF(400, 300, 600, 800);
        arcPaint.setColor(Color.GRAY);
        canvas.drawArc(rectF1, 0, 360, true, arcPaint);

        //画一个线，作为气球的引线
        canvas.drawLine(500, 800, 500, 1200, arcPaint);


    }
}

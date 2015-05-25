package com.ht.zihuikongjian.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by annuo on 2015/5/25.
 */
public class ChartView extends View {

    //画线的paint
    private Paint linePaint;
    private TextPaint textPaint;
    private String[] yTitles;
    private String[] xTitles;
    private Paint rectPaint;
    private int[] lastYearData;
    private int[] thisYearData;

    public ChartView(Context context) {
        super(context);
        init(context, null);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //画线（x轴、y轴、分割线）的paint的初始化
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(2);
        linePaint.setAntiAlias(true);

        //文字的paint的初始化
        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20);

        //y轴文字
        yTitles = new String[]{
                "80000",
                "60000",
                "40000",
                "20000",
                    "0"
        };

        //x轴文字
        xTitles = new String[]{
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7"
        };

        //数据的矩形样式
        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //去年的柱状图数据
        lastYearData = new int[]{
                25000,
                62000,
                11000,
                25000,
                5000,
                45000,
                60000
        };
        //今年的柱状图数据
        thisYearData = new int[]{
                30000,
                70000,
                30000,
                30000,
                60000,
                50000,
                80000
        };

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //*************绘制基准参考内容，y轴

        int height = getHeight();
        //最下面的x轴的标题部分的空间高度
        int BOTTOM_TEXT_HEIGHT = 50;
        int yAxisHeight = height - BOTTOM_TEXT_HEIGHT;
        int startX = 150;
        canvas.drawLine(startX, 0, startX, yAxisHeight, linePaint);

        //**************绘制基准参考内容，x轴

        int width = getWidth();
        canvas.drawLine(startX, yAxisHeight, width, yAxisHeight, linePaint);

        //**************进行每一行坐标线的绘制

        //因为顶部坐标线有一段上边距，所以定义一个变量，设置边距
        int TOP_LINE_MARGIN = 20;
        //行坐标（y轴）总高度
        int realLineHeight = yAxisHeight - TOP_LINE_MARGIN;
        //获取两行坐标线之间的间距，因为进行画线位置的计算
        int perLineHeight = realLineHeight / 4;
        for (int i = 0; i < 4; i++) {
            int lineY = TOP_LINE_MARGIN + (i * perLineHeight);
            canvas.drawLine(startX, lineY, width, lineY, linePaint);
        }

        //***************绘制y轴的标题（右对齐文字）

        textPaint.setTextAlign(Paint.Align.RIGHT);
        int yTitleCount = yTitles.length;
        //y轴与Y轴标题的间距
        int Y_TITLE_Y_MATGIN = 20;
        //获取标题的字体，根据字体获取基线以下的距离，进行移动，确保文字和段落是居中的
        //获取drawText使用的Paint的字体信息
        Paint.FontMetrics metrics = textPaint.getFontMetrics();
        //基线以下的高度
        float descent = metrics.descent;
        for (int i = 0; i < yTitleCount; i++) {
            String title = yTitles[i];
            float stringY = TOP_LINE_MARGIN + (i * perLineHeight) + descent;
            canvas.drawText(
                    title,
                    startX - Y_TITLE_Y_MATGIN,
                    stringY,
                    textPaint
            );
        }

        //***************绘制x轴的标题（居中对齐文字）

        //获取x轴的长度
        int xAxisLength = width - startX;
        //x标题的个数
        int xTitleCount = xTitles.length;
        //因为x轴，标题在轴宽度的内部，因此评分多了一份
        int xColumnNum = xTitleCount + 1;
        //每一列的像素宽度
        int perXSpacing = xAxisLength / xColumnNum;
        //居中对齐
        textPaint.setTextAlign(Paint.Align.CENTER);
        for (int i = 0; i < xTitleCount; i++) {
            String title = xTitles[i];
            int stringX = startX + (i + 1) * perXSpacing;
            canvas.drawText(
                    title,
                    stringX,
                    yAxisHeight + textPaint.getTextSize(),
                    textPaint
            );
        }

        //***************画今年矩形数据
        int thisDataCount = thisYearData.length;
        for (int i = 0; i < thisDataCount; i++) {
            int data = thisYearData[i];
            //计算实际数据代表的矩形高度
            int dataRectLength = realLineHeight * data / 8000;
            //获取每一个x轴的数据所在的中心位置
            int dataCenterX = startX + perXSpacing * (i + 1);
            int left = dataCenterX - 10;
            int right = dataCenterX + 10;
            int top = yAxisHeight - dataRectLength;
            int bottom = yAxisHeight;
            //设置颜色
            //今年的数据颜色
            rectPaint.setColor(Color.rgb(0x10, 0x77, 0xD0));
            canvas.drawRect(left, right, top, bottom, rectPaint);
        }

        //**************画去年矩形数据

        int lastDataCount = lastYearData.length;
        for (int i = 0; i < lastDataCount; i++) {
            int data = lastYearData[i];
            // 计算实际数据代表的矩形高度
            int dataRectLength = realLineHeight * data / 80000;
            // 获取每一个x轴的数据所在的中心位置
            int dataCenterX = startX + perXSpacing * (i + 1);
            int left = dataCenterX - 10; // 矩形宽度 20
            int right = dataCenterX + 10;
            int top = yAxisHeight - dataRectLength;
            int bottom = yAxisHeight; // 矩形底部与 X轴平齐
            // 设置颜色
            // 今年的数据颜色
            rectPaint.setColor(Color.rgb(0x13, 0x92, 0xFF));
            canvas.drawRect(left, top, right, bottom, rectPaint);
        }
    }

    /**
     * 设置去年的数据,对外隐藏内部的具体实现
     * @param data
     */
    public void setLastYearData(int[] data) {
        if (data != null) {
            int dataLen = data.length;
            dataLen = dataLen > 7 ? 7 : dataLen;
            System.arraycopy(data, 0, lastYearData, 0, dataLen);
            postInvalidate();
        }
    }

    /**
     * 设置今年的数据
     * @param data
     */
    public void setThisYearData(int[] data) {
        if (data != null) {
            int dataLen = data.length;
            dataLen = dataLen > 7 ? 7 : dataLen;
            System.arraycopy(data, 0, thisYearData, 0, dataLen);
            postInvalidate();
        }
    }
}

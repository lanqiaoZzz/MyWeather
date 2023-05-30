package com.myweather.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.myweather.android.util.Utility;

public class AirQualityView extends View {

    private int currentAQI;

    public AirQualityView(Context context) {
        super(context);
    }

    public AirQualityView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AirQualityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float density = getResources().getDisplayMetrics().density;
        int radius = (int) (80 * density) / 2;

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2 + radius / 8;

        int[] colors = {Color.MAGENTA, Color.GREEN, Color.YELLOW, Color.RED, Color.MAGENTA};
        float[] positions = {0.0f, 0.4f, 0.55f, 0.9f, 1.0f};

        SweepGradient gradient = new SweepGradient(centerX, centerY, colors, positions);

        Paint paint = new Paint();
        paint.setShader(gradient);
        paint.setStyle(Paint.Style.STROKE);
        float strokeWidth = radius * 0.09f;     // 圆弧宽度
        paint.setStrokeWidth(strokeWidth);

        float startAngle = 135;                 // 圆弧起始角度
        float sweepAngle = 270;

        RectF rect = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        canvas.drawArc(rect, startAngle, sweepAngle, false, paint);

        float circleRadius = strokeWidth;
        float circleAngle = startAngle + sweepAngle * (currentAQI  / 250f);
        float circleX = centerX + (float) Math.cos(Math.toRadians(circleAngle)) * radius;
        float circleY = centerY + (float) Math.sin(Math.toRadians(circleAngle)) * radius;

        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.WHITE);
        circlePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(circleX, circleY, circleRadius, circlePaint);

        String aqiValue = String.valueOf(currentAQI);
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(radius * 0.35f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(aqiValue, centerX, centerY + radius * 0.6f, textPaint);

        String aqiText = Utility.getAQIText(currentAQI).first;
        textPaint.setTextSize(radius * 0.55f);
        canvas.drawText(aqiText, centerX, centerY - radius * 0.1f, textPaint);
    }

    public void setCurrentAQI(int aqi) {
        currentAQI = aqi;
        invalidate();
    }
}

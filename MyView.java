package com.example.chrisbennett.handlinginput;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by chris.bennett on 10/13/16.
 */
public class MyView extends View {

    private Context c;
    private Paint p;
    private Canvas canvas;
    private Bitmap bitmap;
    private float x, y;
    private int radius;
    private ArrayList<Circle> circles;

    private long lastMove = 0;

    private long delay = 1000 / 30;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        c = context;
        p = new Paint();


        p.setColor(Color.BLUE);

        x = 100;
        y = 100;
        radius = 20;

        circles = new ArrayList<Circle>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // your Canvas will draw onto the defined Bitmap
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < circles.size(); i++) {
            canvas.drawCircle(circles.get(i).x,
                    circles.get(i).y,
                    radius,
                    p);
        }
    }

/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

    }
*/


    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        circles.add(new Circle(x, y, 50.0f));
        invalidate();
        return true;
    }

    protected void startTouch(float x, float y) {
        this.x = x;
        this.y = y;
    }

    protected void colorChange(Paint p) {
        p.setColor(Color.RED);

        for (int i = 0; i < circles.size(); i++) {
            canvas.drawCircle(circles.get(i).x,
                    circles.get(i).y,
                    radius,
                    p);
        }
    }

    class Circle {
        public float x;
        public float y;
        public float r;

        public Circle(float x, float y, float r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
}

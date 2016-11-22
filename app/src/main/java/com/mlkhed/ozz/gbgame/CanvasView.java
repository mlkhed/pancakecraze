package com.mlkhed.ozz.gbgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ozz on 13/11/16.
 */

public class CanvasView extends View implements iCanvasView {
    private GameManager gameManager;
    private static int width;
    private static int height;
    private Paint paint;
    private Canvas canvas;
    private Toast toast;
    private Toast countToast;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context);
        gameManager = new GameManager(this, width, height);
        initPaint();

    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowmanager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display display = windowmanager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        gameManager.onDraw();
    }

    @Override
    public void drawCircle(SimpleCircle circle) {
        paint.setColor(circle.getColor());
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if (toast != null) toast.cancel();
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

    @Override
    public void showCount(int count) {
        if (countToast != null) countToast.cancel();
        countToast = Toast.makeText(getContext(), String.valueOf(count), Toast.LENGTH_SHORT);
        countToast.setGravity(Gravity.TOP,0,20);
        countToast.show();
    }

    @Override
    public void equalColor() {
        int enemyCount = 0;
        int foodCount = 0;
        for (EnemyCircle circle : gameManager.circles) {
            if (circle.color == EnemyCircle.ENEMY_COLOR) enemyCount++;
            if (circle.color == EnemyCircle.FOOD_COLOR) foodCount++;
        }
        if (enemyCount == gameManager.circles.size()) gameManager.gameEnd("Gotcha!!!");
        //if (foodCount == gameManager.circles.size()) gameManager.gameEnd("Kill'em All!!");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_MOVE)
        {
            gameManager.onTouchEvent(x,y);
        }
        invalidate();
        return true;
    }
}

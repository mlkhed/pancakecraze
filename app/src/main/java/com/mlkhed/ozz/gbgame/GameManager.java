package com.mlkhed.ozz.gbgame;

import java.util.ArrayList;

/**
 * Created by ozz on 13/11/16.
 */

public class GameManager {

    public static final int MAX_CIRCLES = 20;
    private MainCircle mainCircle;
    private ArrayList<EnemyCircle> circles;
    private CanvasView canvasView;
    private static int width;
    private static int height;

    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        width = w;
        height = h;
        initMainCircle();
        initEnemyCircles();

    }

    private void initEnemyCircles() {
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();
        circles = new ArrayList<>();
        for (int i = 0; i< MAX_CIRCLES; i++)
        {
            EnemyCircle circle;
            do
            {
                circle = EnemyCircle.getRandomCircle();
            } while (circle.isIntersectWith(mainCircleArea));
            circles.add(circle);
        }
        calculateAndSetCirclesColor();
    }

    private void calculateAndSetCirclesColor() {
        for (EnemyCircle circle : circles) {
            circle.setColorDependsOn(mainCircle);
            
        }
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }


    private void initMainCircle() {
        mainCircle = new MainCircle(width /2, height /2);
    }

    protected void onDraw() {
        canvasView.drawCircle(mainCircle);
        for (EnemyCircle circle : circles) {
            canvasView.drawCircle(circle);

        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveOnTouch(x,y);
        checkCollisions();
        moveCircle();
    }

    private void checkCollisions() {
        SimpleCircle circleForDelet = null;
        for (EnemyCircle circle : circles) {
            if (mainCircle.isIntersectWith(circle)) {
                if (circle.isSmallerThan(mainCircle)) {
                    mainCircle.Grow(circle);
                    circleForDelet = circle;
                    calculateAndSetCirclesColor();
                    break;
                } else {
                    gameEnd("OUCH!!!!");
                    return;
                }
            }
        }
        if (circleForDelet != null) circles.remove(circleForDelet);
        if (circles.isEmpty()) gameEnd("DONE!!");
    }

    private void gameEnd(String text) {
        canvasView.showMessage(text);
        mainCircle.initRadius();
        initEnemyCircles();
        canvasView.redraw();
    }

    private void moveCircle() {
        for (EnemyCircle circle : circles) {
            circle.moveOneStep();
        }
    }
}

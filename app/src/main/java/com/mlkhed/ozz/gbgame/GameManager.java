package com.mlkhed.ozz.gbgame;

import java.util.ArrayList;

/**
 * Created by ozz on 13/11/16.
 */

public class GameManager {

    public static final int MAX_CIRCLES = 20;
    private MainCircle mainCircle;
    protected ArrayList<EnemyCircle> circles;
    private CanvasView canvasView;
    private static int width;
    private static int height;
    private int count;

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
                    mainCircle.growUp(circle);
                    circleForDelet = circle;
                    calculateAndSetCirclesColor();
                    break;
                } else {
                    mainCircle.growDown(circle);
                    circleForDelet = circle;
                    calculateAndSetCirclesColor();
                    break;
                    //gameEnd("OUCH!!!!");
                    //return;
                }
                //count++;
                //canvasView.showCount(count);
            }
        }
        if (circleForDelet != null) circles.remove(circleForDelet);
        if (circles.isEmpty()) gameEnd("WIN!");
        if (canvasView.noMoreFood()) gameEnd("NO MORE FOOD!");
        if (mainCircle.radius < GameManager.getWidth()/EnemyCircle.MIN_RADIUS) gameEnd("FAIL");
    }

    protected void gameEnd(String text) {
        canvasView.showMessage(text);
        mainCircle.initRadius();
        initEnemyCircles();
        canvasView.redraw();
        //count = 0;
    }

    private void moveCircle() {
        for (EnemyCircle circle : circles) {
            circle.moveOneStep();
        }
    }
}


// TODO: 22/11/16 все фичи в отдельные ветки гита
// TODO: 21/11/16 если выполнится проверка, то пусть шар зальет весь экран своим цветом. а потом гейменд()
// TODO: 21/11/16 speedtest : на место изсчезнувших кругов, инициализируется новый. главный растет только до определенного размера потом сбрасывается, и счетчик считает +1 и прибавляет скорость.
// TODO: 21/11/16 agilitytest : режим сурвайвл - плотность растет с каждым столкновением. удаляем один прибавляем 2.
// TODO: 22/11/16 сделать эффект перетекания массы из шара в шар.
// TODO: 22/11/16 натянуть на шары картинки.

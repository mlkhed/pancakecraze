package com.mlkhed.ozz.gbgame;

import android.graphics.Color;


/**
 * Created by ozz on 13/11/16.
 */

public class MainCircle extends SimpleCircle {
    public static final int INIT_RADIUS = 50;
    public static final int MAIN_SPEED = 81;
    public static final int MAIN_COLOR = Color.rgb(0, 170, 255);
    public static final double GROWUP_SPEED_SLOWER = 5;
    public static final double GROWDOWN_SPEED_SLOWER = 10;


    public MainCircle(int x, int y) {
        super(x, y, INIT_RADIUS);
        setColor(MAIN_COLOR);
    }

    public void moveOnTouch(int x1, int y1) {
        int dx = (int) ((x1-x) * MAIN_SPEED / GameManager.getWidth());
        int dy = (int) ((y1-y) * MAIN_SPEED / GameManager.getHeight());
        x += dx;
        y += dy;
    }

    public void initRadius() {
        radius = INIT_RADIUS;
    }

    public void growUp(SimpleCircle circle) {
        //новая площадь круга равноа сумме предыдущих плащадей отсюда радиус нового круга равен pi* Rnew^2 = pi* Rold^2 + pi* Reat^2
        radius += (int) ((Math.sqrt(Math.pow(radius, 2) + Math.pow(circle.radius, 2))) / GROWUP_SPEED_SLOWER);
    }

    public void growDown(SimpleCircle circle) {
        //уменьшаем круг на столько насколько напавший круг больше
        radius -= (int) ((Math.sqrt(Math.pow(radius, 2) + Math.pow(circle.radius, 2))) / GROWDOWN_SPEED_SLOWER);;
    }
}


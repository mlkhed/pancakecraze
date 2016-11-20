package com.mlkhed.ozz.gbgame;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by ozz on 16/11/16.
 */

public class EnemyCircle extends SimpleCircle {

    public static final int MIN_RADIUS = 70; //делитель миниммального радиуса, радиус = ширине экрана / делитель
    public static final int MAX_RADIUS = 10; //делитель максимального радиуса

    public static final int FOOD_COLOR = Color.rgb(125, 255, 0);
    public static final int ENEMY_COLOR = Color.rgb(255, 125, 0);
    public static final int MAX_ENEMY_SPEED = 10;
    private int dy;
    private int dx;

    public EnemyCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }


    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());
        int y = random.nextInt(GameManager.getHeight());
        int dx = -MAX_ENEMY_SPEED + random.nextInt(MAX_ENEMY_SPEED)*2 +1;
        int dy = -MAX_ENEMY_SPEED + random.nextInt(MAX_ENEMY_SPEED)*2 +1;
        int radius = GameManager.getWidth()/ MIN_RADIUS + (random.nextInt(GameManager.getWidth())/ MAX_RADIUS);
        EnemyCircle enemyCircle;
        enemyCircle = new EnemyCircle(x,y,radius, dx, dy);
        return enemyCircle;
    }

    public void setColorDependsOn(MainCircle mainCircle) {
        if(isSmallerThan(mainCircle)){
            setColor(FOOD_COLOR);
        } else
            setColor(ENEMY_COLOR);


    }

    public boolean isSmallerThan(SimpleCircle circle) {
        if (radius <= circle.radius){
            return true;
        }
        return false;
    }

    public void moveOneStep() {
        x += dx;
        y += dy;
        checkBounds();
    }

    private void checkBounds() {
        if (x > GameManager.getWidth() || x < 0) dx = -dx;
        if (y > GameManager.getHeight() || y < 0) dy = -dy;
    }
}

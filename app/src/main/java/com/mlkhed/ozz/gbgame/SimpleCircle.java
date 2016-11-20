package com.mlkhed.ozz.gbgame;

/**
 * Created by ozz on 16/11/16.
 */

public class SimpleCircle {
    protected int x;
    protected int y;
    protected int radius;
    protected int colorR;
    protected int colorG;
    protected int colorB;
    protected int color;

    public SimpleCircle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor (int color){
        this.color = color;
    }

    public SimpleCircle getCircleArea() {
        return new SimpleCircle(x,y,radius*3);
    }

    public boolean isIntersectWith(SimpleCircle circle) {
        return radius + circle.radius >= Math.sqrt(Math.pow(x-circle.x, 2) + Math.pow(y-circle.y, 2));  // сравниваем сумму радиусов с гипотенузой
                                                                                                        // (корень квадратный из суммы квадратов).
    }

/*    public void setColor(char colorR, char colorG, char colorB) {
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
    }*/

}

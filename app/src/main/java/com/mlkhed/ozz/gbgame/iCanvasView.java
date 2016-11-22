package com.mlkhed.ozz.gbgame;

import java.util.ArrayList;

/**
 * Created by ozz on 14/11/16.
 */

public interface iCanvasView {
    void drawCircle(SimpleCircle circle);

    void redraw();

    void showMessage(String text);

    void showCount(int count);

    boolean noMoreFood();
}

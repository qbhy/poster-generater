package com.qbhy.poster.contracts;

import java.awt.*;

public interface DrawableInterface {
    void draw(Graphics2D gd);

    int getZIndex();
}

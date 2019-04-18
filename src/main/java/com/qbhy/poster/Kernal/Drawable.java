package com.qbhy.poster.Kernal;

import com.qbhy.poster.Contracts.DrawableInterface;

public abstract class Drawable extends JsonAble implements DrawableInterface {

    /**
     * z index 值
     */
    private int zIndex = 1;

    public int getZIndex() {
        return zIndex;
    }
}

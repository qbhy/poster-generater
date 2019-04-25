package com.qbhy.poster.kernal;

import com.qbhy.poster.contracts.DrawableInterface;
import com.qbhy.poster.drawable.Text;

import java.io.IOException;
import java.net.URL;

public abstract class Drawable extends JsonAble implements DrawableInterface {

    /**
     * z index 值
     */
    private int zIndex = 1;

    public int getZIndex() {
        return zIndex;
    }
}

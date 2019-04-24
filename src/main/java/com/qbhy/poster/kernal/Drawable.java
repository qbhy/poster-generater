package com.qbhy.poster.kernal;

import com.qbhy.poster.contracts.DrawableInterface;
import com.qbhy.poster.drawable.Text;

public abstract class Drawable extends JsonAble implements DrawableInterface {

    /**
     * z index 值
     */
    private int zIndex = 1;

    public int getZIndex() {
        return zIndex;
    }

    public static String getResourcePath(String fontName) {
        return Text.class.getClassLoader().getResource(fontName).getPath();
    }
}

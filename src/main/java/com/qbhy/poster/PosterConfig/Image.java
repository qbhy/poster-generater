package com.qbhy.poster.PosterConfig;

import com.qbhy.poster.Kernal.Drawable;

import java.awt.*;

public class Image extends Drawable  {

    /**
     * x 值
     */
    private Integer x;

    /**
     * y 值
     */
    private Integer y;

    /**
     * 宽度
     */
    private Integer width;

    /**
     * 高度
     */
    private Integer height;

    /**
     * border radius
     */
    private Integer borderRadius;

    /**
     * url
     */
    private String url;

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getBorderRadius() {
        return borderRadius;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void draw(Graphics2D gd) {

    }
}

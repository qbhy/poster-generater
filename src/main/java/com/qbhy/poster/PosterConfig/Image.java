package com.qbhy.poster.PosterConfig;

import com.qbhy.poster.Contracts.Drawable;

public class Image implements Drawable {

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
     * z index 值
     */
    private Integer zIndex;

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
    public void draw() {

    }

    @Override
    public Integer getzIndex() {
        return this.zIndex;
    }
}

package com.qbhy.poster.PosterConfig;

import com.qbhy.poster.Contracts.Drawable;

public class Block implements Drawable {
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


    @Override
    public void draw() {

    }

    /**
     * 画布背景颜色
     */
    private String backgroudColor;

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

    public String getBackgroudColor() {
        return backgroudColor;
    }

    @Override
    public Integer getzIndex() {
        return this.zIndex;
    }

}

package com.qbhy.poster.PosterConfig;

import com.qbhy.poster.Contracts.Drawable;

public class Line implements Drawable {

    private Float startX;// 开始 x 坐标
    private Float endX; // 结束 x 坐标
    private Float startY; // 开始 y 坐标
    private Float endY; // 结束 y 坐标
    private Integer width; // 宽度
    private String color; // 颜色
    private Integer zIndex; //z index 值

    public Float getStartX() {
        return startX;
    }

    public Float getEndX() {
        return endX;
    }

    public Float getStartY() {
        return startY;
    }

    public Float getEndY() {
        return endY;
    }

    public Integer getWidth() {
        return width;
    }

    public String getColor() {
        return color;
    }

    @Override
    public Integer getzIndex() {
        return zIndex;
    }

    @Override
    public void draw() {

    }
}

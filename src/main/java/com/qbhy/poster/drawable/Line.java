package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.ColorTools;
import com.qbhy.poster.kernal.Drawable;

import java.awt.*;

public class Line extends Drawable {

    private int startX;// 开始 x 坐标
    private int endX; // 结束 x 坐标
    private int startY; // 开始 y 坐标
    private int endY; // 结束 y 坐标
    private Integer width; // 宽度
    private String color = "#000000"; // 颜色
    private Integer zIndex; //z index 值

    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndY() {
        return endY;
    }

    public Integer getWidth() {
        return width;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void draw(Graphics2D gd) {
        gd.setPaint(ColorTools.String2Color(this.getColor())); // 设置画笔颜色
        gd.drawLine(this.getStartX(), this.getStartY(), this.getEndX(), this.getEndY()); // 划线

    }
}

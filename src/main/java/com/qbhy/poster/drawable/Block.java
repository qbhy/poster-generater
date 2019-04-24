package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.ColorTools;
import com.qbhy.poster.kernal.Drawable;

import java.awt.*;

public class Block extends Drawable {

    /**
     * x 值
     */
    private int x = 0;

    /**
     * y 值
     */
    private int y = 0;

    /**
     * 宽度
     */
    private Integer width;

    /**
     * 高度
     */
    private Integer height;

    /**
     * 画布背景颜色
     */
    private String backgroudColor;

    /**
     * 边框颜色
     */
    private String borderColor = "#000";

    /**
     * 边框粗细
     */
    private int borderWidth = 0;

    public int getBorderWidth() {
        return borderWidth;
    }

    @Override
    public void draw(Graphics2D gd) {
        if (backgroudColor != null) {
            gd.setColor(ColorTools.String2Color(this.getBackgroudColor())); // 设置画笔颜色
            gd.fillRect( this.getX(), this.getY(), this.getWidth(), this.getHeight()); // 画填充矩形
        } else if (borderWidth > 0) {
            gd.setColor(ColorTools.String2Color(this.getBorderColor())); // 设置画笔颜色
            gd.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight()); // 画边框矩形
        }
    }


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

    public String getBorderColor() {
        return borderColor;
    }

    public String getBackgroudColor() {
        return backgroudColor;
    }

}

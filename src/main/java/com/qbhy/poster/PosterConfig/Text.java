package com.qbhy.poster.PosterConfig;

import com.qbhy.poster.Contracts.Drawable;

public class Text implements Drawable {

    @Override
    public void draw() {

    }

    private Float x;//  x 坐标
    private Float y; // y 坐标
    private Integer fontSize; // 字体大小
    private Integer width; // 文本域宽度
    private Integer lineHeight; // 行高
    private String color; // 颜色
    private Integer zIndex = 1; //z index 值
    private String text; // 文本内容
    private Integer opacity = 1; // 透明度

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getLineHeight() {
        return lineHeight;
    }

    public String getColor() {
        return color;
    }

    public String getText() {
        return text;
    }

    public Integer getOpacity() {
        return opacity;
    }

    public String getTextAlign() {
        return textAlign;
    }

    private String textAlign = "center"; // 文本对齐方式

    @Override
    public Integer getzIndex() {
        return this.zIndex;
    }

}

package com.qbhy.poster.PosterConfig;

import com.qbhy.poster.Kernal.ColorTools;
import com.qbhy.poster.Kernal.Drawable;

import java.awt.*;

public class Text extends Drawable {

    @Override
    public void draw(Graphics2D gd) {
        gd.setFont(new Font("Default", Font.PLAIN, this.getFontSize()));
        gd.setColor(ColorTools.String2Color(this.getColor()));
        gd.drawString(this.getText(), this.getX(), this.getY() + this.getFontSize());
    }

    private int x = 0;//  x 坐标
    private int y = 0; // y 坐标
    private Integer fontSize = 24; // 字体大小
    private Integer width; // 文本域宽度
    private Integer lineHeight; // 行高
    private String color = "#000"; // 颜色
    private String text; // 文本内容
    private Integer opacity = 1; // 透明度

    public int getX() {
        return x;
    }

    public int getY() {
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

}

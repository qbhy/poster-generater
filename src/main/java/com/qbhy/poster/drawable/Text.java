package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.ColorTools;
import com.qbhy.poster.kernal.Drawable;
import com.qbhy.poster.kernal.ResourceUtils;
import sun.font.FontDesignMetrics;

import java.awt.*;
import java.io.IOException;

public class Text extends Drawable {

    @Override
    public void draw(Graphics2D gd) throws IOException {
        Font font;

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, ResourceUtils.getFontFile(this.getFont())).deriveFont((float) this.getFontSize());
        } catch (Exception e) {
            throw new IOException("font" + getFont() + " error!");
        }

        gd.setFont(font);
        gd.setColor(ColorTools.String2Color(this.getColor()));
        int offset = 0;
        if (textAlign.equals("center")) {
            offset = -Text.getWordWidth(font, this.getText()) / 2;
        }
        gd.drawString(this.getText(), this.getX() + offset, this.getY() + this.getFontSize());
    }

    public static int getWordWidth(Font font, String content) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int width = 0;
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return width;
    }

    private int x = 0;//  x 坐标
    private int y = 0; // y 坐标
    private Integer fontSize = 24; // 字体大小
    private Integer width; // 文本域宽度
    private Integer lineHeight; // 行高
    private String color = "#000"; // 颜色
    private String text; // 文本内容
    private Integer opacity = 1; // 透明度
    private String textAlign = "left"; // 文本对齐方式
    private String font = "pingfangsr"; // 字体

    public String getFont() {
        return font;
    }

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


}

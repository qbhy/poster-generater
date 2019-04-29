package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.ColorTools;
import com.qbhy.poster.kernal.Drawable;
import com.qbhy.poster.kernal.ResourceUtils;
import lombok.Data;
import sun.font.FontDesignMetrics;
import java.awt.*;
import java.io.IOException;

@Data
public class Text extends Drawable {

    @Override
    public void draw(Graphics2D gd) throws IOException {
        Font drawFont;

        try {
            drawFont = Font.createFont(Font.TRUETYPE_FONT, ResourceUtils.getFontFile(font)).deriveFont((float) fontSize);
        } catch (Exception e) {
            drawFont = new Font("Default", Font.PLAIN, fontSize);
        }

        gd.setFont(drawFont);
        gd.setColor(ColorTools.String2Color(color));
        int offset = 0;
        if (textAlign.equals("center")) {
            offset = -Text.getWordWidth(drawFont, text) / 2;
        }
        gd.drawString(text, x + offset, y + fontSize);
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

}

package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.ColorTools;
import com.qbhy.poster.kernal.Drawable;
import com.qbhy.poster.kernal.ResourceUtils;
import lombok.Data;
import sun.font.FontDesignMetrics;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Data
public class Text extends Drawable {

    static Map<String, Font> drawFonts = new HashMap<>();

    public static Font getDrawFont(String name, Integer fontSize) {
        Font tempFont = drawFonts.get(name);
        if (tempFont == null) {
            try {
                tempFont = Font.createFont(Font.TRUETYPE_FONT, ResourceUtils.getFontFile(name)).deriveFont((float)fontSize);
            } catch (Exception e) {
                tempFont = new Font("Default", Font.PLAIN, fontSize);
            }
            drawFonts.put(name, tempFont);
            return tempFont;
        }

        if (tempFont.getSize() != fontSize) {
            return tempFont.deriveFont((float)fontSize);
        }

        return tempFont;
    }

    @Override
    public void draw(Graphics2D gd, int posterWidth, int posterHeight) {
        Font drawFont = getDrawFont(font, fontSize);

        // 设置字体和颜色
        gd.setFont(drawFont);
        gd.setColor(ColorTools.String2Color(color));
        //消除锯齿状 - 由微信群友【菠萝蜜】提供
        gd.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        // 文本域实际宽度
        int textWidth = width + x > posterWidth ? posterWidth - x : (width == 0 ? posterWidth : width);
        // 当前行数
        int currentNum = 0;
        // 当前行应该取到第几个文字
        int lineTextIndex;

        // 剩余没有画的图片
        String surplus = text;
        // 当前行文本
        String lineText;
        // 是否最后一行
        boolean lastLine;
        // 是否需要省略号
        boolean ellipsis = textOverflow.equals("ellipsis");

        // x 轴偏移量
        int offsetX = 0;

        // 当前行文本宽度
        int lineTextWidth;
        lineHeight = lineHeight <= 0 ? fontSize : lineHeight;

        // 一行一行画
        while (surplus.length() > 0 && currentNum < lineNum) {
            lineTextWidth = getWordWidth(drawFont, surplus);
            lastLine = currentNum + 1 == lineNum;
            // 放不下的时候,就要对文本进行裁剪了
            if (lineTextWidth > textWidth) {
                lineTextIndex = interceptALine(surplus, drawFont, textWidth, lastLine, ellipsis);
                lineText = surplus.substring(0, lineTextIndex); // 截取当前行文本
                surplus = surplus.substring(lineTextIndex); // 记录剩余文本
                lineTextWidth = getWordWidth(drawFont, lineText); // 重新计算宽度
            } else {
                lineText = surplus;
                surplus = ""; // 画完了
            }

            if (textAlign.equals("center")) {
                offsetX = (textWidth - lineTextWidth) / 2;
            }
            if (textAlign.equals("right")) {
                offsetX = (textWidth - lineTextWidth);
            }

            if (lastLine && ellipsis && surplus.length() > 0) {
                lineText = lineText + "...";
            }

            gd.drawString(lineText, x + offsetX, y + fontSize + (lineHeight - fontSize) / 2 + currentNum * lineHeight);
            currentNum++;
        }
    }

    /**
     * 截取一行的文本，获取索引
     *
     * @param surplus  剩余文本
     * @param drawFont 字体
     * @param lastLine 是否最后一行
     * @param ellipsis 是否溢出显示省略号
     * @return int 返回索引
     */
    private int interceptALine(String surplus, Font drawFont, int textWidth, boolean lastLine, boolean ellipsis) {
        int fullTextWidth = getWordWidth(drawFont, surplus);
        // 如果是最后一行且溢出且需要省略号，则预留省略号宽度
        textWidth = fullTextWidth > textWidth && lastLine && ellipsis ? textWidth - getWordWidth(drawFont, "...") : textWidth;

        // 默认值,后面试用循环不断调整为最佳值
        int lineIndex = surplus.length() - (fullTextWidth - textWidth) / fontSize;

        String lineText = surplus.substring(0, lineIndex);
        int lineTextWidth = getWordWidth(drawFont, lineText);

        // 循环到文本宽度小于文本域宽度为止
        while (lineTextWidth > textWidth) {
            lineIndex--;
            lineText = surplus.substring(0, lineIndex);
            lineTextWidth = getWordWidth(drawFont, lineText);
        }

        return lineIndex;
    }

    /**
     * 获取文本所占宽度
     *
     * @param font    字体
     * @param content 文本内容
     * @return int 返回宽度
     */
    private static int getWordWidth(Font font, String content) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int width = 0;
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return width;
    }

    /**
     * z index 值
     */
    private int index = 1;

    public int getZIndex() {
        return index;
    }

    @NotNull(message = "文本X坐标不能为空")
    private int x = 0;//  x 坐标
    @NotNull(message = "文本Y坐标不能为空")
    private int y = 0; // y 坐标
    @Min(value = 8, message = "文本字体大小不能小于8")
    private Integer fontSize = 24; // 字体大小
    @Min(value = 0, message = "文本区域宽度不能小于0")
    private Integer width = 0; // 文本域宽度
    @Min(value = 0, message = "文本行高不能小于0")
    private Integer lineHeight = 0; // 行高

    @Min(value = 1, message = "文本行数不能小于1")
    private Integer lineNum = 1; // 行数

    @NotNull(message = "文本字体颜色不能为null")
    private String color = "#000000"; // 颜色

    @NotEmpty(message = "文本内容不能为空")
    private String text; // 文本内容

    @Min(value = 0, message = "文本透明度不能小于0")
    @Max(value = 1, message = "文本透明度能大于1")
    private Integer opacity = 1; // 透明度

    private String textAlign = "left"; // 文本对齐方式
    private String font = "pingfangsr"; // 字体
    private String textOverflow = "ellipsis"; // 文本溢出默认省略号

}

package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.ColorTools;
import com.qbhy.poster.kernal.Drawable;
import lombok.Data;

import java.awt.*;
import java.io.IOException;

@Data
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

    @Override
    public void draw(Graphics2D gd) throws IOException {
        if (backgroudColor != null) {
            gd.setColor(ColorTools.String2Color(backgroudColor)); // 设置画笔颜色
            gd.fillRect(x, y, width, height); // 画填充矩形
        } else if (borderWidth > 0) {
            gd.setStroke(new BasicStroke((float) borderWidth)); // 设置画笔大小
            gd.setColor(ColorTools.String2Color(borderColor)); // 设置画笔颜色
            gd.drawRect(x, y, width, height); // 画边框矩形
        }
    }


}

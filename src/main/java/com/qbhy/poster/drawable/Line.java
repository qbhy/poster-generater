package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.ColorTools;
import com.qbhy.poster.kernal.Drawable;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.IOException;

@Data
public class Line extends Drawable {

    /**
     * z index 值
     */
    private int index = 1;
    public int getZIndex() {
        return index;
    }

    @NotNull(message = "开始X坐标不能为空")
    private int startX;// 开始 x 坐标
    @NotNull(message = "结束X坐标不能为空")
    private int endX; // 结束 x 坐标
    @NotNull(message = "开始Y坐标不能为空")
    private int startY; // 开始 y 坐标
    @NotNull(message = "结束Y坐标不能为空")
    private int endY; // 结束 y 坐标

    @Min(value = 1)
    private int width = 1; // 宽度

    private String color = "#000000"; // 颜色

    @Override
    public void draw(Graphics2D gd, int posterWidth, int posterHeight) throws IOException {
        if (width > 0) {
            gd.setStroke(new BasicStroke((float) width));
            gd.setPaint(ColorTools.String2Color(color)); // 设置画笔颜色
            gd.drawLine(startX, startY, endX, endY); // 划线
        }
    }
}

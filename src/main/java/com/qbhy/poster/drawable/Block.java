package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.ColorTools;
import com.qbhy.poster.kernal.Drawable;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.io.IOException;

@Data
public class Block extends Drawable {

    /**
     * x 值
     */
    @NotNull(message = "区块X坐标不能为空")
    private int x = 0;

    /**
     * y 值
     */
    @NotNull(message = "区块Y坐标不能为空")
    private int y = 0;

    /**
     * 宽度
     */
    @NotNull(message = "区块宽度不能为空")
    private Integer width;

    /**
     * 高度
     */
    @NotNull(message = "区块高度不能为空")
    private Integer height;

    /**
     * 画布背景颜色
     */
    private String backgroudColor;

    /**
     * 边框颜色
     */
    private String borderColor = "#000000";

    /**
     * 边框粗细
     */
    @Min(value = 0, message = "区块边框大小不能小于0")
    private int borderWidth = 0;

    /**
     * 盒子模型,支持 contentBox 或者 borderBox
     */
    private String boxSizing = "borderBox";

    @Override
    public void draw(Graphics2D gd, int posterWidth, int posterHeight) {

        int offset = boxSizing.equals("borderBox") ? 0 : borderWidth;

        if (backgroudColor != null) {
            gd.setColor(ColorTools.String2Color(backgroudColor)); // 设置画笔颜色
            gd.fillRect(x + offset, y + offset, width, height); // 画填充矩形
        }

        if (borderWidth > 0) {
            gd.setStroke(new BasicStroke((float) borderWidth)); // 设置画笔大小
            gd.setColor(ColorTools.String2Color(borderColor)); // 设置画笔颜色
            gd.drawRect(x + borderWidth / 2, y + borderWidth / 2, width - borderWidth + offset * 2, height - borderWidth + offset * 2); // 画边框矩形
        }
    }


}

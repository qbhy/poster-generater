package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.Drawable;
import com.qbhy.poster.kernal.ResourceUtils;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

@Data
public class Image extends Drawable {

    @Override
    public void draw(Graphics2D gd, int posterWidth, int posterHeight) throws IOException {
        // 获取图片
        BufferedImage image = ResourceUtils.getImage(url);

        // 如果宽高不合适，先缩放
        if (image.getWidth() != width || image.getHeight() != height) {
            System.out.println("如果宽高不合适，先缩放");
            image = resize(image, width, height);
        }

        // 处理圆角
        if (borderRadius > 0) {
            image = ResourceUtils.setRadius(image, borderRadius * 4, 0, 0);
        }

        // 画图
        gd.drawImage(image, x, y, width, height, new ImageObserver() {
            @Override
            public boolean imageUpdate(java.awt.Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }

    /**
     * 缩放图片
     *
     * @param image  需要缩放的图片
     * @param width  宽
     * @param height 高
     * @return BufferedImage
     */
    private static BufferedImage resize(BufferedImage image, int width, int height) {
        java.awt.Image img = image.getScaledInstance(width, height, java.awt.Image.SCALE_FAST);

        BufferedImage newBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = newBufferedImage.createGraphics();
        graphics.drawImage(img, 0, 0, null);
        graphics.dispose();

        return newBufferedImage;
    }

    /**
     * x 值
     */
    @NotNull(message = "图片X坐标不能为空")
    private Integer x;

    /**
     * y 值
     */
    @NotNull(message = "图片Y坐标不能为空")
    private Integer y;

    /**
     * 宽度
     */
    @NotNull(message = "图片宽度不能为空")
    private Integer width;

    /**
     * 高度
     */
    @NotNull(message = "图片高度不能为空")
    private Integer height;

    /**
     * border radius
     */
    @Min(value = 0, message = "图片圆角不能小于0")
    private Integer borderRadius = 0;

    /**
     * url
     */
    @NotEmpty(message = "图片url不能为空")
    private String url;

}

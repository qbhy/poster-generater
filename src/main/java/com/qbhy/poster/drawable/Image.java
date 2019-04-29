package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.Drawable;
import com.qbhy.poster.kernal.ResourceUtils;
import lombok.Data;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

@Data
public class Image extends Drawable {

    @Override
    public void draw(Graphics2D gd) throws IOException {
        // 获取图片
        BufferedImage image = ResourceUtils.getImage(url);

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
     * x 值
     */
    private Integer x;

    /**
     * y 值
     */
    private Integer y;

    /**
     * 宽度
     */
    private Integer width;

    /**
     * 高度
     */
    private Integer height;

    /**
     * border radius
     */
    private Integer borderRadius = 0;

    /**
     * url
     */
    private String url;

}

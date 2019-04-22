package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.Drawable;
import com.qbhy.poster.kernal.ImageTools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Image extends Drawable {

    @Override
    public void draw(Graphics2D gd) {
        try {
            // 获取图片
            BufferedImage image = ImageTools.getImage(this.getUrl());

            // 处理圆角
            if (borderRadius > 0) {
                image = ImageTools.setRadius(image, borderRadius*4, 0, 0);
            }

            System.out.println("borderRadius:" + borderRadius);

            // 画图
            gd.drawImage(image, this.getX(), getY(), this.getWidth(), this.getHeight(), new ImageObserver() {
                @Override
                public boolean imageUpdate(java.awt.Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("图片写入失败，请检查URL：" + this.getUrl());
        }
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

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getBorderRadius() {
        return borderRadius;
    }

    public String getUrl() {
        return url;
    }

}

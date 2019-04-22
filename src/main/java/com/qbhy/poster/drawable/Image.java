package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.Drawable;
import com.qbhy.poster.kernal.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Image extends Drawable {

    @Override
    public void draw(Graphics2D gd) {
        try {
            BufferedImage image = ImageLoader.getImage(this.getUrl());

            gd.drawImage(image, this.getX(), getY(), this.getWidth(), this.getHeight(), new ImageObserver() {
                @Override
                public boolean imageUpdate(java.awt.Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        } catch (Exception e) {
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
    private Integer borderRadius;

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

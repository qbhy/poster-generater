package com.qbhy.poster.PosterConfig;

import com.qbhy.poster.Kernal.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Image extends Drawable {

    @Override
    public void draw(Graphics2D gd) {
        try {
            BufferedImage image = ImageIO.read(new URL("https://cdn.uootu.com/ddd"));
        } catch (IOException e) {

        }

        System.out.println(System.getProperty("user.dir"));
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

package com.qbhy.poster.kernal;

import com.qbhy.poster.Config;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ImageLoader {

    @Autowired
    private Config config;

    public static BufferedImage getImage(String url) throws Exception {
        if (url.contains("://")) {
            return ImageLoader.getImageFromUrl(url);
        }

        File imageFile = new File(Drawable.getResourcePath("template/" + url));
        if (imageFile.exists()) {
            return ImageIO.read(imageFile);
        }

        throw new IIOException("Can't get input stream from URL!");
    }

    public static BufferedImage getImageFromUrl(String url) throws Exception {
        BufferedImage image = ImageIO.read(new URL(url));

        // 存起来

        return image;
    }

}
package com.qbhy.poster;

import com.qbhy.poster.kernal.Drawable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.DigestUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Configuration
@ConfigurationProperties(prefix = "poster")
@PropertySource(value = "classpath:poster.properties")
public class Config {

    /**
     * 下载路径
     */
    private String downloadPath;

    /**
     * 获取文件下载目录
     *
     * @return String
     */
    public String getDownloadPath() {
        return downloadPath + (downloadPath.endsWith("/") ? "" : "/");
    }

    /**
     * 获取文件下载后的地址
     *
     * @param url
     *
     * @return String
     */
    public String getDownloadPath(String url) {
        return getDownloadPath() + url2fileName(url);
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    /**
     * 从模板中获取图片
     *
     * @param imageName
     *
     * @return imageFile
     *
     * @throws IOException
     */
    public BufferedImage getTemplateImage(String imageName) throws IOException {
        File imageFile = new File(Drawable.getResourcePath("template/" + imageName));
        if (imageFile.exists()) {
            return ImageIO.read(imageFile);
        }

        throw new IOException("file not found!");
    }

    public File getDownloadedFile(String url) {
        File imageFile = new File(getDownloadPath(url));

        if (imageFile.exists()) {
            return imageFile;
        }

        return null;
    }

    public String url2fileName(String url) {
        return DigestUtils.md5DigestAsHex(url.getBytes()) + ".png";
    }


}

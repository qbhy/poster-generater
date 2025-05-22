package com.qbhy.poster.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSaver
{
    /**
     * 保存图片到当前工作目录
     * @param image     要保存的图像对象 (BufferedImage)
     * @param format    图片格式 (png/jpg等)
     * @param fileName  文件名（无需后缀）
     * @return 保存后的完整路径
     */
    public static String saveToCurrentDir(BufferedImage image, String format, String fileName) throws IOException {
        // 获取当前工作目录
        String currentDir = System.getProperty("user.dir");

        // 构建完整保存路径
        String fullPath = currentDir + File.separator + fileName + "." + format;
        File outputFile = new File(fullPath);

        // 执行保存操作
        if (!ImageIO.write(image, format, outputFile)) {
            throw new IllegalArgumentException("不支持的图片格式: " + format);
        }

        return outputFile.getAbsolutePath();
    }
}

package com.qbhy.poster.drawable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qbhy.poster.kernal.Drawable;
import com.qbhy.poster.kernal.ResourceUtils;

import java.awt.geom.AffineTransform;

import com.qbhy.poster.utils.NumberUtils;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Data
public class Image extends Drawable {

    @Override
    public void draw(Graphics2D gd, int posterWidth, int posterHeight) throws IOException {
        BufferedImage image;

        if (qrCode) {
            // 是二维码图片的话创建一个二维码
            try {
                image = createQrCode(url, width, height, qrCodeMargin);
            } catch (WriterException e) {
                throw new IOException("生成二维码失败", e);
            }
        } else {
            // 获取图片
            image = ResourceUtils.getImage(url);
        }

        // 如果宽高不合适，先缩放
        if ((image.getWidth() != width || image.getHeight() != height)) {
            image = resize(image, width, height);
        }

        // 要旋转的话旋转
        if (angle != 0) {

            x -= NumberUtils.convert(width * scaleX, NumberUtils.ROUND, width);

            image = rotateImage(image, angle);

            width = image.getWidth();
            height = image.getHeight();
        }

        // 处理圆角，todo: 实现的不完美，待优化
        if (borderRadius > 0) {
            image = ResourceUtils.setRadius(image, borderRadius * 4, 0, -1);
        }

        // 画图
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gd.drawImage(image, x, y, width, height, (img, infoFlags, x, y, width, height) -> false);
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

        BufferedImage newBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = newBufferedImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawImage(img, 0, 0, null);
        graphics.dispose();

        return newBufferedImage;
    }

    public static BufferedImage rotateImageCentered(BufferedImage original, double angleDegrees) {
        // 将角度转换为弧度
        double radians = Math.toRadians(angleDegrees);

        // 计算旋转后的新尺寸（避免裁剪）
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));

        int newWidth = (int) Math.round(original.getWidth() * cos + original.getHeight() * sin);
        int newHeight = (int) Math.round(original.getWidth() * sin + original.getHeight() * cos);

        // 创建新图像（保持原始图像类型）
        int type = original.getTransparency() == BufferedImage.OPAQUE ?
                BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, type);

        // 创建图形上下文
        Graphics2D g2d = rotated.createGraphics();

        // 设置渲染质量
        g2d.setRenderingHint(
                java.awt.RenderingHints.KEY_INTERPOLATION,
                java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );
        g2d.setRenderingHint(
                java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON
        );

        // 计算中心点坐标
        int centerX = newWidth / 2;
        int centerY = newHeight / 2;
        int origCenterX = original.getWidth() / 2;
        int origCenterY = original.getHeight() / 2;

        // 应用变换：围绕中心旋转
        AffineTransform transform = new AffineTransform();
//        transform.translate(centerX, centerY);         // 移动到新中心
        transform.rotate(radians);                     // 围绕中心旋转
//        transform.translate(-origCenterX, -origCenterY); // 偏移使原始中心对齐

        g2d.setTransform(transform);

        // 绘制原始图像
        g2d.drawImage(original, 0, 0, null);

        // 清理资源
        g2d.dispose();

        return rotated;
    }

    public static BufferedImage rotateImageFromTopLeft(BufferedImage original, double angleDegrees) {
        // 将角度转换为弧度
        double radians = Math.toRadians(angleDegrees);

        // 计算旋转后的新尺寸（避免裁剪）
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.round(original.getWidth() * cos + original.getHeight() * sin);
        int newHeight = (int) Math.round(original.getWidth() * sin + original.getHeight() * cos);

        // 创建新图像（保持原始图像类型）
        int type = original.getTransparency() == BufferedImage.OPAQUE ?
                BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage rotated = new BufferedImage(newWidth, newHeight, type);

        // 创建图形上下文
        Graphics2D g2d = rotated.createGraphics();

        // 设置渲染质量
        g2d.setRenderingHint(
                java.awt.RenderingHints.KEY_INTERPOLATION,
                java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );
        g2d.setRenderingHint(
                java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON
        );

        // 应用变换：基于左上角旋转
        AffineTransform transform = new AffineTransform();
        transform.rotate(radians, 0, 0); // 旋转中心为左上角(0,0)
        g2d.setTransform(transform);

        // 绘制原始图像
        g2d.drawImage(original, 0, 0, null);

        // 清理资源
        g2d.dispose();

        return rotated;
    }

    /**
     * 旋转图片
     */
    public static BufferedImage rotateImage(BufferedImage original, double angleDegrees) {
        // 将角度转换为弧度
        double radians = Math.toRadians(angleDegrees);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));

        // 计算新画布尺寸（避免旋转后裁剪）
        int newWidth = (int) Math.round(original.getWidth() * cos + original.getHeight() * sin);
        int newHeight = (int) Math.round(original.getWidth() * sin + original.getHeight() * cos);

        // 创建新画布（带透明度支持）
        BufferedImage rotated = new BufferedImage(
                newWidth,
                newHeight,
                BufferedImage.TYPE_INT_ARGB
        );

        // 获取图形上下文
        Graphics2D g2d = rotated.createGraphics();

        // 设置渲染质量
        g2d.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );

        // 计算旋转中心（新画布中心）
        int centerX = newWidth / 2;
        int centerY = newHeight / 2;

        // 移动坐标系到中心点
        AffineTransform at = new AffineTransform();
        at.translate(centerX, centerY);
        at.rotate(radians);
        // 调整绘制位置（使原图中心对准坐标系原点）
        at.translate(-original.getWidth() / 2, -original.getHeight() / 2);

        // 应用变换
        g2d.setTransform(at);

        // 绘制图像
        g2d.drawImage(original, 0, 0, null);

        // 释放资源
        g2d.dispose();

        return rotated;
    }


    private double angle = 0;

    private double scaleX = 0;

    /**
     * z index 值
     */
    private int index = 1;

    public int getZIndex() {
        return index;
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

    /**
     * 是否是二维码图片
     */
    private boolean qrCode = false;

    /**
     * 二维码边距
     */
    @Min(value = 0, message = "二维码边距最小为0")
    private int qrCodeMargin = 2;

    /**
     * 创建二维码
     *
     * @param content 二维码内容
     * @param width   宽度
     * @param height  高度
     * @param margin  二维码边距
     * @return BufferedImage 返回图片
     * @throws WriterException 异常
     */
    public static BufferedImage createQrCode(String content, int width, int height, int margin) throws WriterException {

        Map<EncodeHintType, Comparable> hints = new HashMap<>();

        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 字符串编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // 纠错等级
        hints.put(EncodeHintType.MARGIN, margin); // 图片边距
        QRCodeWriter writer = new QRCodeWriter();

        return MatrixToImageWriter.toBufferedImage(writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints));
    }

    /**
     * 创建二维码
     *
     * @param content 二维码内容
     * @param width   宽度
     * @param height  高度
     * @return BufferedImage 返回图片
     * @throws WriterException 异常
     */
    public static BufferedImage createQrCode(String content, int width, int height) throws WriterException {
        return createQrCode(content, width, height, 2);
    }


    public static void main(String[] args) {
        // 指定测试图片路径
        String inputPath = "downloads/82f3551664c08202fc7bcbf5b99b132b.png";
        String outputPath = "rotated_image.png";

        try {
            // 1. 加载测试图片
            System.out.println("正在加载图片: " + inputPath);
            File inputFile = new File(inputPath);
            BufferedImage original = ImageIO.read(inputFile);

            if (original == null) {
                System.err.println("无法加载图片，请检查路径: " + inputPath);
                return;
            }

            System.out.println("图片加载成功! 尺寸: " + original.getWidth() + "x" + original.getHeight());

            // 2. 测试多个旋转角度
            double[] testAngles = {0, 45, 90, 180, 270, 15, -30};

            for (double angle : testAngles) {
                String angleOutputPath = getAngleOutputPath(outputPath, angle);
                System.out.println("\n旋转角度: " + angle + "°");

                // 3. 执行旋转
                long startTime = System.currentTimeMillis();
                BufferedImage rotated = rotateImage(original, angle);
                long duration = System.currentTimeMillis() - startTime;

                // 4. 保存结果
                File outputFile = new File(angleOutputPath);
                ImageIO.write(rotated, "PNG", outputFile);

                System.out.println("旋转完成! 耗时: " + duration + "ms");
                System.out.println("新尺寸: " + rotated.getWidth() + "x" + rotated.getHeight());
                System.out.println("结果已保存: " + angleOutputPath);
            }

            System.out.println("\n所有旋转测试完成!");

        } catch (Exception e) {
            System.err.println("处理过程中发生错误:");
            e.printStackTrace();
        }
    }

    // 为不同角度生成不同的输出文件名
    private static String getAngleOutputPath(String basePath, double angle) {
        // 提取文件名和扩展名
        int dotIndex = basePath.lastIndexOf('.');
        String name = (dotIndex == -1) ? basePath : basePath.substring(0, dotIndex);
        String ext = (dotIndex == -1) ? "" : basePath.substring(dotIndex);

        // 格式化角度值
        String angleStr = String.valueOf(angle)
                .replace("-", "neg")
                .replace(".", "p");

        return name + "_" + angleStr + "deg" + ext;
    }
}

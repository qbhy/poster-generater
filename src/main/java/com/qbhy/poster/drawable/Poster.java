package com.qbhy.poster.drawable;

import com.qbhy.poster.kernal.ColorTools;
import com.qbhy.poster.kernal.Drawable;
import com.qbhy.poster.kernal.JsonAble;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poster extends JsonAble {

    /**
     * 画布宽度
     */
    private int width;

    /**
     * 画布高度
     */
    private int height;

    /**
     * 画布背景颜色
     */
    private String backgroundColor;

    /**
     * 文本列表
     */
    private ArrayList<Text> texts;

    /**
     * 图片列表
     */
    private ArrayList<Image> images;

    /**
     * 矩形列表
     */
    private ArrayList<Block> blocks;

    /**
     * 线列表
     */
    private ArrayList<Line> lines;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public ArrayList<Text> getTexts() {
        return texts;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    protected void push2map(Map<Integer, ArrayList<Drawable>> indexMap, Drawable drawable) {
        ArrayList<Drawable> drawables = indexMap.get(drawable.getZIndex());
        drawables = drawables == null ? new ArrayList<>() : drawables;
        drawables.add(drawable);
        indexMap.put(drawable.getZIndex(), drawables);
    }

    public void draw() throws Exception {

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // create graphics
        Graphics2D gd = image.createGraphics();

        Map<Integer, ArrayList<Drawable>> indexMap = new HashMap<>();
        ArrayList<Drawable> drawables;

        if (backgroundColor != null) {
//            gd.setBackground(ColorTools.String2Color(this.getBackgroundColor()));
            gd.setColor(ColorTools.String2Color(this.getBackgroundColor()));
            gd.fillRect(0, 0, width, height);
        }

        if (this.blocks != null) {
            // 遍历 blocks
            for (Block block : this.blocks) {
                push2map(indexMap, block);
            }
        }

        if (this.lines != null) {
            // 遍历 lines
            for (Line line : this.lines) {
                push2map(indexMap, line);
            }
        }

        if (this.images != null) {
            // 遍历 images
            for (Image img : this.images) {
                push2map(indexMap, img);
            }
        }

        if (this.texts != null) {
            // 遍历 texts
            for (Text text : this.texts) {
                push2map(indexMap, text);
            }
        }

        // 按 index 顺序执行绘画过程
        for (Integer index : indexMap.keySet()) {
            drawables = indexMap.get(index);
            for (Drawable drawable : drawables) {
                drawable.draw(gd);
            }
        }

        gd.dispose();
        ImageIO.write(image, "PNG", new File("/Users/xiejianlai/Pictures/java.png"));

        System.out.println("绘制完毕");
    }
}

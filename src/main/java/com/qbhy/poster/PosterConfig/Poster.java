package com.qbhy.poster.PosterConfig;

import java.util.ArrayList;

public class Poster {

    /**
     * 画布宽度
     */
    private Integer width;

    /**
     * 画布高度
     */
    private Integer height;

    /**
     * 画布背景颜色
     */
    private String backgroudColor;

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

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public String getBackgroudColor() {
        return backgroudColor;
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
}

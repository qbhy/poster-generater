package com.qbhy.poster.contracts;

import java.awt.*;
import java.io.IOException;

public interface DrawableInterface {
    /**
     * 绘制内容
     *
     * @param gd           graphics 实例
     * @param posterWidth  海报宽度
     * @param posterHeight 海报高度
     * @throws IOException 通常是资源异常
     */
    void draw(Graphics2D gd, int posterWidth, int posterHeight) throws IOException;

    int getZIndex();
}

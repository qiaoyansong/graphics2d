package com.qiaoyansong.util.graphics2dutil.graph.image;

import java.awt.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/9/6 8:44 下午
 * description：
 */
public class Paintbrush implements AutoCloseable {

    private final Image image;

    private final Graphics2D graphics;

    private final Color oldColor;
    private final Font oldFont;

    Paintbrush(Image image) {
        this.image = image;
        this.graphics = image.bufferedImage().createGraphics();
        this.oldColor = graphics.getColor();
        this.oldFont = graphics.getFont();
    }

    /**
     * 将图片画在当前图片之上
     */
    public Paintbrush draw(Image frontImage, int x, int y) {
        graphics.drawImage(frontImage.bufferedImage(), x, y, frontImage.width(), frontImage.height(), null);
        return this;
    }

    /**
     * 将文本画在当前图片之上
     */
    public Paintbrush draw(String text, Color color, Font font, int x, int y) {
        try {
            graphics.setColor(color);
            graphics.setFont(font);
            FontMetrics fontMetrics = graphics.getFontMetrics();
            // 字符宽度
            int stringWidth = fontMetrics.stringWidth(text);
            graphics.drawString(text, x, y);
        } finally {
            reset();
        }
        return this;
    }

    public Paintbrush drawCenter(String text, Color color, Font font, int centerX, int y) {
        try {
            graphics.setColor(color);
            graphics.setFont(font);
            FontMetrics fontMetrics = graphics.getFontMetrics();
            // 字符宽度
            int stringWidth = fontMetrics.stringWidth(text);
            graphics.drawString(text, centerX - stringWidth / 2, y);
        } finally {
            reset();
        }
        return this;
    }

    /**
     * 画圆角
     *
     * @param radius 弧度
     * @return this
     */
    public Paintbrush fillRoundCorner(int rectx, int recty, int width, int height, int radius) {
        graphics.setComposite(AlphaComposite.Src);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRoundRect(rectx, recty, width, height, radius, radius);
        return this;
    }

    private void reset() {
        graphics.setColor(oldColor);
        graphics.setFont(oldFont);
    }

    /**
     * 释放此图形上下文并释放它正在使用的任何系统资源。图形对象在调用释放后无法使用。
     *
     * @return image from {@link #Paintbrush(Image)}
     */
    public Image closebrush() {
        graphics.dispose();
        return image;
    }

    @Override
    public void close() {
        closebrush();
    }
}

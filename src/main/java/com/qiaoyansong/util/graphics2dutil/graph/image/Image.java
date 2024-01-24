package com.qiaoyansong.util.graphics2dutil.graph.image;

import com.google.common.base.Throwables;
import com.qiaoyansong.util.graphics2dutil.common.Classpath;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/9/6 8:40 下午
 * description：表示一个图片
 */
public class Image {

    public static Image wrap(BufferedImage image) {
        return new Image(image);
    }

    /**
     * 基于字节数组创建图片
     */
    public static Image fromBytes(byte[] image) {
        try (InputStream in = new ByteArrayInputStream(image)) {
            return open(in);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * 打开图片
     */
    public static Image open(File file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            return new Image(bufferedImage);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * 打开图片
     */
    public static Image open(InputStream in) {
        try {
            BufferedImage bufferedImage = ImageIO.read(in);
            return new Image(bufferedImage);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * 打开图片
     */
    public static Image openClasspath(String classpath) {
        try (InputStream in = Classpath.load(classpath)) {
            BufferedImage bufferedImage = ImageIO.read(in);
            return new Image(bufferedImage);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * 底层图片
     */
    private final BufferedImage image;

    protected Image(BufferedImage image) {
        this.image = image;
    }

    /**
     * 以png格式写入流
     */
    public Image writePng(OutputStream out) {
        write(out, "PNG");
        return this;
    }

    /**
     * 以png格式写入流
     */
    public Image writePng(File file) {
        write(file, "PNG");
        return this;
    }

    /**
     * 以png格式写入流
     */
    public Image writePng(String file) {
        writePng(new File(file));
        return this;
    }

    /**
     * 以jpg格式写入流
     */
    public Image writeJpg(OutputStream out) {
        write(out, "JPG");
        return this;
    }

    /**
     * 以jpg格式写入文件
     */
    public Image writeJpg(File file) {
        write(file, "JPG");
        return this;
    }

    /**
     * 以jpg格式写入文件
     */
    public Image writeJpg(String file) {
        writeJpg(new File(file));
        return this;
    }

    public byte[] toByteArray(String format) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            write(out, format);
            return out.toByteArray();
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    private void write(OutputStream out, String format) {
        try {
            ImageIO.write(image, format, out);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * 以png格式写入流
     */
    private Image write(File file, String format) {
        try (OutputStream out = new FileOutputStream(file)) {
            write(out, format);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
        return this;
    }

    public int height() {
        return image.getHeight();
    }

    public int width() {
        return image.getWidth();
    }

    public Paintbrush paintbrush() {
        return new Paintbrush(this);
    }

    protected BufferedImage bufferedImage() {
        return image;
    }
}

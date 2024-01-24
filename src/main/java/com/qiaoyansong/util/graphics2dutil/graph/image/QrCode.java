package com.qiaoyansong.util.graphics2dutil.graph.image;

import com.google.common.base.Throwables;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/9/6 8:46 下午
 * description：
 */
public class QrCode extends Image{
    private static final String CHARSET = "utf-8";

    /**
     * 创建二维码
     *
     * @param text      二维码内容
     * @param qrcodeArc 二维码大小
     */
    public static QrCode create(String text, int qrcodeArc) {

        // 二维码参数设置
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 安全等级，最高h
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 编码设置
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        // 设置margin=0-10
        hints.put(EncodeHintType.MARGIN, 0);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, qrcodeArc, qrcodeArc, hints);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            return new QrCode(image);
        } catch (WriterException e) {
            throw Throwables.propagate(e);
        }
    }

    protected QrCode(BufferedImage image) {
        super(image);
    }

    public QrCode coverTo(Image image, int x, int y) {
        try (Paintbrush paintbrush = image.paintbrush()) {
            paintbrush.draw(this, x, y);
        }
        return this;
    }

    /**
     * 将二维码打印到控制台
     *
     * @param out print stream
     */
    public QrCode print(PrintStream out) {
        BufferedImage img = bufferedImage();
        int width = img.getWidth();
        int height = img.getHeight();
        for (int y = 0; y < height; y += 4) {
            for (int x = 0; x < width; x += 4) {
                int pixel = img.getRGB(x, y);
                if (pixel == -1) { // 黑色
                    out.print(' ');
                } else {
                    out.print('▇');
                }
            }
            out.println();
        }
        return this;
    }

    public QrCode print() {
        print(System.out);
        return this;
    }
}

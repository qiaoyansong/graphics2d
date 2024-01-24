package com.qiaoyansong.util.graphics2dutil.graph;

import com.google.common.base.Throwables;
import com.qiaoyansong.util.graphics2dutil.common.Classpath;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/9/6 8:52 下午
 * description：
 */
public class FontUtils {

    private FontUtils() {
    }

    /**
     * PingFang 字体
     */
    public static final Font PING_FANG;

    static {
        try (InputStream in = Classpath.load("fonts/PingFang.ttc")) {
            PING_FANG = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (IOException | FontFormatException e) {
            throw Throwables.propagate(e);
        }
    }
}

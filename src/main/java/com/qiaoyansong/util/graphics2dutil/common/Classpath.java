package com.qiaoyansong.util.graphics2dutil.common;

import com.google.common.base.Throwables;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2023/9/6 8:45 下午
 * description：
 */
public class Classpath {

    private Classpath() {
    }

    private static final DefaultResourceLoader RESOURCE_LOADER = new DefaultResourceLoader(Classpath.class.getClassLoader());

    public static byte[] loadBytes(String path) {
        Resource resource = RESOURCE_LOADER.getResource("classpath:" + path);

        try (InputStream in = resource.getInputStream()) {
            return ByteStreams.toByteArray(in);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    public static String loadString(String path) {
        Resource resource = RESOURCE_LOADER.getResource("classpath:" + path);

        try (Reader in = new InputStreamReader(resource.getInputStream())) {
            return CharStreams.toString(in);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    public static InputStream load(String path) {
        Resource resource = RESOURCE_LOADER.getResource("classpath:" + path);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }
}

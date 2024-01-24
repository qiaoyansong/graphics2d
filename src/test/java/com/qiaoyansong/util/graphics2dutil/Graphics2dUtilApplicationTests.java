package com.qiaoyansong.util.graphics2dutil;

import com.qiaoyansong.util.graphics2dutil.graph.image.Image;
import com.qiaoyansong.util.graphics2dutil.graph.image.QrCode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Graphics2dUtilApplication.class })
class Graphics2dUtilApplicationTests {

    @Test
    public void test() {
        // 生成二维码
        QrCode qrCode = QrCode.create("测试", 380);
        String imgClassPath = "xt/xt_share_background.png";
        Image pictureBytes = Image.openClasspath(imgClassPath)
                .paintbrush()
                .draw(qrCode, 214, 403)
//                .draw(discountDescPrefix, Color.WHITE, FontUtils.PING_FANG.deriveFont(Font.BOLD, 90), 80, 323)
//                .drawCenter(couponInfo.getCouponVal(), Color.WHITE, FontUtils.PING_FANG.deriveFont(Font.BOLD, 90), valueCenterX, 323)
//                .draw(couponInfo.getCouponMsg(), Color.WHITE, FontUtils.PING_FANG.deriveFont(Font.BOLD, 90), unitX, 323)
                .closebrush()
                .writePng(new File("2.png"));
    }

}

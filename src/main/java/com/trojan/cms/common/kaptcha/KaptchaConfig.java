package com.trojan.cms.common.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import java.util.Properties;

/**
 * Kaptcha配置类，用于生成验证码
 * 该类封装了验证码生成器的配置和文本生成逻辑
 */
public class KaptchaConfig {
    
    /**
     * 获取配置好的DefaultKaptcha实例
     * 该方法初始化并配置了一个DefaultKaptcha实例，用于生成验证码图像
     * 主要配置了验证码的边框、颜色、字体、图像尺寸等属性
     *
     * @return 配置好的DefaultKaptcha实例
     */
    public static DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        //边框
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.border.color", "blue");
        //文本颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        //图片大小
        properties.setProperty("kaptcha.image.width", "160");
        properties.setProperty("kaptcha.image.height", "50");
        //文字尺寸
        properties.setProperty("kaptcha.textproducer.font.size", "40");
        properties.setProperty("kaptcha.session.key", "code");
        //干扰噪点
//        properties.setProperty("kaptcha.noise.color", "white");
//        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
    
    /**
     * 生成验证码文本
     * 该方法通过调用getDefaultKaptcha方法获取验证码生成器实例，并生成验证码文本
     *
     * @return 生成的验证码文本
     */
    public String getText(){
        return getDefaultKaptcha().createText();
    }
    
}

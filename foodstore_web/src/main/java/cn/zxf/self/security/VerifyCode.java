package cn.zxf.self.security;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by sunjunchen on 2016/12/9.
 */
public class VerifyCode {
    //系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /**
     * 输出随机验证码图片流,并返回验证码值
     * @param w
     * @param h
     * @param os
     * @return
     * @throws IOException
     */
    public static String outputVerifyImage(int w, int h, OutputStream os) throws IOException{
        int codesLen = VERIFY_CODES.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(4);
        for(int i = 0; i < 4; i++){
            verifyCode.append(VERIFY_CODES.charAt(rand.nextInt(codesLen-1)));
        }
        outputImage(w, h, os, verifyCode.toString());
        return verifyCode.toString();
    }

    /**
     * 输出指定验证码图片流
     * @param w
     * @param h
     * @param os
     * @param code
     * @throws IOException
     */
    private static void outputImage(int w, int h, OutputStream os, String code) throws IOException{
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.GRAY);// 设置边框色
        g2.fillRect(0, 0, w, h);

        g2.setColor(Color.WHITE);// 设置背景色
        g2.fillRect(1, 1, w-2, h-2);

        g2.setColor(Color.BLACK);
        int fontSize = h-4;
        Font font = new Font("黑体", Font.ITALIC, fontSize);
        g2.setFont(font);
        char[] chars = code.toCharArray();
        for(int i = 0; i < verifySize; i++){
            g2.drawChars(chars, i, 1, ((w-10) / verifySize) * i + 5, h/2 + fontSize/2);
        }

        g2.dispose();
        ImageIO.write(image, "png", os);
    }
}

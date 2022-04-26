package com.jhj.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Slf4j
public class RandomCodeUtil {

    private static final String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random random = new Random();

    @Data
    public class CodeConfig{
        /**
         * 图片宽
         */
        private int width = 95;
        /**
         * 图片高
         */
        private int height = 25;
        /**
         * 干扰线数量
         */
        private int lineSize = 40;

        private int stringNum = 4;

        public CodeConfig(int stringNum){
            this.stringNum = stringNum;
        }
    }

    /**
     * 随机产生数字与字母组合的字符串
     */



    @Setter
    private CodeConfig codeConfig;

    @Getter
    @Setter
    private  BufferedImage image;

    @Getter
    @Setter
    private  String randomString;

    public RandomCodeUtil(CodeConfig config){
        this.codeConfig = config;
    }

    public RandomCodeUtil(int stringNum){
        this.codeConfig = new CodeConfig(stringNum);
    }

    public RandomCodeUtil(){}

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.BOLD, 18);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {

        fc = fc > 255? 255:fc;
        bc = bc > 255? 255:bc;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     */
    public void createRandcode() {
        if(codeConfig != null){
            // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
            image = new BufferedImage(codeConfig.getWidth(), codeConfig.getHeight(), BufferedImage.TYPE_INT_BGR);
            // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
            Graphics g = image.getGraphics();
            //图片大小
            g.fillRect(0, 0, codeConfig.getWidth(), codeConfig.getHeight());
            //字体大小
            g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
            //字体颜色
            g.setColor(getRandColor(110, 133));

            // 绘制干扰线
            for (int i = 0; i <= codeConfig.getLineSize(); i++) {
                drawLine(g, codeConfig.getWidth(), codeConfig.getHeight());
            }

            // 绘制随机字符
            this.randomString = "";
            for (int i = 1; i <= codeConfig.getStringNum(); i++) {
                randomString = drawString(g, randomString, i);
            }
            log.info(randomString);
            g.dispose();
        }

    }

    /**
     * 绘制字符串
     */
    private String drawString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = getRandomString(random.nextInt(randString.length()));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private void drawLine(Graphics g, int width, int height) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    private String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

}

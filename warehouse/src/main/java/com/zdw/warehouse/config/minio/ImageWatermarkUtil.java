package com.zdw.warehouse.config.minio;

import cn.hutool.core.util.StrUtil;
import com.google.common.io.Resources;
import com.zdw.common.constant.minio.MinioConstants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * 上传的图片增加水印
 * @author 卓德文
 * @since 2022-11-11
 */
@SuppressWarnings("all")
public class ImageWatermarkUtil {

    /**
     * 内部枚举
     */
    /*public enum positionTypeEnum{
        BOTTOM_RIGHT("1","右下角");

        private String key;
        private String value;

        private positionTypeEnum(String key,String value){
            this.key=key;
            this.value=value;
        }
    }*/

    /**
     * 加文字水印
     * @param bufImg
     * @param img
     * @param markImg       水印图片
     * @param width         水印图片宽度
     * @param height        水印图片高度
     * @param x             横坐标，相对于源图片
     * @param y             纵坐标，同上
     * @param size          文本大小
     */
    public static void mark(BufferedImage bufImg, Image img, String text, Font font, Color color, int width, int height,int size) {

        Graphics2D g = bufImg.createGraphics();

        //在设置画笔添加水印前,先添加空画笔,填充整个图片
        g.setColor(null);
        g.fillRect(0, 0, width, height);

        g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
        g.setColor(color);
        g.setFont(font);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, MinioConstants.DEFAULT_ALPHA));
        g.rotate(Math.toRadians(MinioConstants.DEFAULT_RADIANS), (double) bufImg.getWidth() / 2, (double) bufImg.getHeight() / 2);

        //获取文本水印的真正高度与宽度
        int wid = size * getLength(text);
        int hei = size * getLength(text);

        int x = -wid;
        int y = -hei;
        while (x < width*1.5) {
            y = -hei;
            while (y < height*1.5) {
                y += hei;
                g.drawString(text, x, y);
            }
            x += wid*2;
        }

        g.dispose();
    }

    /**
     * 加图片水印
     * @param bufImg
     * @param img
     * @param markImg       水印图片
     * @param width         水印图片宽度
     * @param height        水印图片高度
     * @param x             横坐标，相对于源图片
     * @param y             纵坐标，同上
     */
    public static void mark(BufferedImage bufImg, Image img, Image markImg, int width, int height, int x, int y) {
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(img, 0, 0, bufImg.getWidth(), bufImg.getHeight(), null);
        g.drawImage(markImg, x, y, width, height, null);
        g.dispose();
    }

    /**
     * 给图片增加文字水印
     * @param imgPath       要添加水印的图片路径
     * @param outImgPath    输出路径
     * @param text          文字
     * @param color         颜色
     * @param x             文字位于当前图片的横坐标
     * @param y             文字位于当前图片的竖坐标
     */
    public static void mark(String imgPath, String outImgPath, String text, Color color, int x, int y) {
        try {
            // 读取原图片信息
            File imgFile = null;
            Image img = null;
            if (imgPath != null) {
                imgFile = new File(imgPath);
            }
            if (imgFile != null && imgFile.exists() && imgFile.isFile() && imgFile.canRead()) {
                img = ImageIO.read(imgFile);
            }
            int imgWidth = img.getWidth(null);
            int imgHeight = img.getHeight(null);
            // 加水印
            BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
            int size=imgWidth/text.length();
            Font font = new Font(MinioConstants.DEFAULT_FONT_NAME, Font.HANGING_BASELINE, size);
            mark(bufImg, img, text, font, color, 0, imgHeight-50,size);
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(outImgPath);
            ImageIO.write(bufImg, MinioConstants.CONTENT_TYPE_JPG, outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给图片增加图片水印
     * @param inputImg      源图片，要添加水印的图片
     * @param markImg       水印图片
     * @param outputImg     输出图片(可以是源图片)
     * @param width         水印图片宽度
     * @param height        水印图片高度
     * @param x             横坐标，相对于源图片
     * @param y             纵坐标，同上
     */
    public void mark(String inputImg, String markImg, String outputImg, int width, int height, int x, int y) {
        // 读取原图片信息
        File inputImgFile = null;
        File markImgFile = null;
        Image img = null;
        Image mark = null;
        try {
            if (inputImg != null && markImg != null) {
                inputImgFile = new File(inputImg);
                markImgFile = new File(markImg);
            }
            if (inputImgFile != null && inputImgFile.exists() && inputImgFile.isFile() && inputImgFile.canRead()) {
                img = ImageIO.read(inputImgFile);
            }
            if (markImgFile != null && markImgFile.exists() && markImgFile.isFile() && markImgFile.canRead()) {
                mark = ImageIO.read(markImgFile);
            }
            int imgWidth = img.getWidth(null);
            int imgHeight = img.getHeight(null);
            BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
            mark(bufImg, img, mark, width, height, 0, imgHeight-50);
            FileOutputStream outImgStream = new FileOutputStream(outputImg);
            ImageIO.write(bufImg, MinioConstants.CONTENT_TYPE_JPG, outImgStream);
            outImgStream.flush();
            outImgStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * MultipartFile加文字水印
     * @param multipartFile     文件对象
     * @param dSuffix           文件后缀
     * @param dOriginFileName   文件名称
     * @param dContentType      文件类型
     * @return                  MultipartFile对象
     * @throws IOException
     */
    public MultipartFile multipartFileAddWater(MultipartFile multipartFile, String dSuffix, String dOriginFileName, String dContentType, String waterMark){
        InputStream inputImg=null;
        InputStream is=null;
        ByteArrayOutputStream bs =null;
        ImageOutputStream imOut=null;
        try{
            inputImg=multipartFile.getInputStream();
            //获取图片
            Image img = ImageIO.read(inputImg);
            //宽度和高度
            int imgWidth = img.getWidth(null);
            int imgHeight = img.getHeight(null);

            BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);

            //水印文字
            if(StrUtil.isBlank(waterMark)){
                waterMark= MinioConstants.DEFAULT_WATER_MARK;
            }

            //字体大小
            int size=10;
            if(imgWidth>=1024 && imgWidth<5120){
                size=20;
            }else if(imgWidth>=5120){
                size=50;
            }

            //获取中文字体
            Font font =getFont(size);

            //调用mark方法加文字水印
            mark(bufImg, img, waterMark,font, MinioConstants.DEFAULT_COLOR, imgWidth, imgHeight,size);

            //输出流转输入流,再转成MultipartFile对象
            bs =new ByteArrayOutputStream();
            imOut =ImageIO.createImageOutputStream(bs);
            ImageIO.write(bufImg,dSuffix,imOut);
            imOut.flush();
            is = new ByteArrayInputStream(bs.toByteArray());
            FileItem fileItem=createFileItem(is,dContentType,dOriginFileName);
            multipartFile = new CommonsMultipartFile(fileItem);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(is!=null){
                    is.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
            try {
                if(imOut!=null){
                    imOut.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
            try {
                if(bs!=null){
                    bs.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
            try {
                if(inputImg!=null){
                    inputImg.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return multipartFile;
    }

    /**
     * InputStream创建FileItem
     * @param fis                   流
     * @param dContentType          文件类型
     * @param dOriginFileName       文件名称
     * @return                      FileItem
     */
    private static FileItem createFileItem(InputStream fis, String dContentType, String dOriginFileName) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem(dOriginFileName, dContentType, true, dOriginFileName);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * 计算文本长度
     * @param text  文本
     * @return      长度
     */
    private static int getLength(String text) {
        int returnLength=text.length();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            String s = String.valueOf(text.charAt(i));
            if (s.getBytes().length > 1) returnLength++;
        }
        returnLength = returnLength % 2 == 0 ? returnLength / 2 : returnLength / 2 + 1;
        return returnLength;
    }

    /**
     * 获取中文字体(微软雅黑)
     * @param size  字体大小
     * @return      字体对象
     */
    private static Font getFont(float size) {
        Font font=null;
        InputStream is = null;
        try{
            is =  Resources.class.getClassLoader().getResourceAsStream(MinioConstants.DEFAULT_FONT_MSYH);;
            font = Font.createFont(Font.PLAIN, is).deriveFont(size);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return font;
    }

}

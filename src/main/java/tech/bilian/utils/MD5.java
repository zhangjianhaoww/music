package tech.bilian.utils;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

public class MD5 {

    /**
     * 对传入的String进行MD5加密
     *
     * @param s
     * @return
     */
    public static final String getMd5(String s) {
        // 16进制数组
        char hexDigits[] = {'5', '0', '5', '6', '2', '9', '6', '2', '5', 'q', 'b', 'l', 'e', 's', 's', 'y'};
        try {
            char str[];
            // 将传入的字符串转换成byte数组
            byte strTemp[] = s.getBytes();
            // 获取MD5加密对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            // 传入需要加密的目标数组
            mdTemp.update(strTemp);
            // 获取加密后的数组
            byte md[] = mdTemp.digest();
            int j = md.length;
            str = new char[j * 2];
            int k = 0;
            // 将数组做位移
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            // 转换成String并返回
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(MD5.getMd5("123456"));
        GenerateImage("3ca8b24999bc86c97a3a8dabd55765d731ac7009acc6ec68b4f5df9524443835", "/home/haru/zhng.jpeg");
    }


    public static boolean GenerateImage(String imgStr, String saveImgFilePath) {
        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;

        OutputStream out = null;
        try {
            //Base64解码
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0)
                    b[i] += 256;//调整异常数据
            }
            //生成jpeg图片
            out = new FileOutputStream(saveImgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return false;
        }
    }
}
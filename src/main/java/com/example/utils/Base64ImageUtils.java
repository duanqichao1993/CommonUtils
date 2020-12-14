package com.example.utils;

import java.io.*;
import java.util.Base64;

/**
 * 通用Base 图片转码工具
 */
class Base64ImageUtils {


    public static String GetImageStr(String imgFilePath) throws IOException {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        // 对字节数组Base64编码
        Base64.Encoder encoder = Base64.getEncoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encodeToString(data);
    }

    public static boolean GenerateImage(String imgStr, String imgFilePath) throws IOException {
        // 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) {
            return false;
        }
        OutputStream out = null;
        try {
            // Base64解码
            byte[] bytes = Base64.getDecoder().decode(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
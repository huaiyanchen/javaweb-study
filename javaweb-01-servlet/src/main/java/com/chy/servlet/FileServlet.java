package com.chy.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1. 下载文件的路径
        String realPath = "E:\\IdeaWorkBench\\javaweb-study\\javaweb-01-servlet\\src\\main\\resources\\1.png";
        System.out.println("下载文件的路径" + realPath);
        //  2. 文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //  3. 设置想办法让浏览器能够支持下载我们需要的东西
        resp.setHeader("Content-disposition", "attachment;filename=" + fileName);
        //  4. 获取下载文件的输入流
        FileInputStream fileInputStream = new FileInputStream(realPath);
        //  5. 创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        //  6. 获取OutPutStream对象
        ServletOutputStream outputStream = resp.getOutputStream();
        //  7. 讲FileOutPutStream流写入到 buffer缓冲区,使用OutPutStream将缓冲区中的数据输出到客户端
        while ((len = fileInputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        fileInputStream.close();
        outputStream.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static void main(String[] args) {
        String s1 = "E:/IdeaWorkBench/javaweb-study/javaweb-01-servlet/src/main/resources/1.png";
        System.out.println(s1.lastIndexOf('/'));
        System.out.println("s1" + s1.substring(s1.lastIndexOf('/') + 1));
    }
}

package Servlet;

import compress.ZipUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/startCompress")
public class startCompress extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet-startCompress");

        String path = "E:\\ider_web\\day9\\out\\artifacts\\day9_war_exploded\\newFile";
        String fileName = "newFile.zip";
        boolean b1=ZipUtil.zip(path,fileName);  //调用ZIPUtil下的压缩函数

        File file1 = new File("F:\\apache-tomcat-9.0.17\\bin\\newFile.zip");
        File file2 = new File("E:\\ider_web\\day9\\out\\artifacts\\day9_war_exploded\\resource\\newFile.zip");

        file1.deleteOnExit();//在程序结束时删除文件1
        try {
            file2.createNewFile();
        } catch (IOException e) {
            System.out.println("移动失败");
            e.printStackTrace();
        }
        ZipUtil.cutFile(file1, file2);      //调用ZIPUtil下的文件移动函数

        if(b1)  //压缩成功
        {
            response.sendRedirect("compressSuccess.html");
        }else {
            response.sendRedirect("compressFail.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

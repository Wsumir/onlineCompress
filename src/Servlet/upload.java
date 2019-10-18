package Servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/upload")
public class upload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int result = 0;
//        System.out.println("*****************upload-Servlet*****************");
        //判断请求是非是否是multipart请求
       if(!ServletFileUpload.isMultipartContent(request))
       {
           System.out.println("请求无效");
           throw new RuntimeException("请求无效");

       }
        try{
            System.out.println("进入try");
            //创建磁盘文件项工厂
            DiskFileItemFactory factory =new DiskFileItemFactory();
            //创建文件上传核心组件
            ServletFileUpload upload= new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");

            //解析请求，获取所有的Item
            List<FileItem> items = upload.parseRequest(request);
            //遍历items
            for(FileItem item :items){
//              判断是否形成字段，如果isFormField()==true,就是说形成字段，也就是普通类型，否则isFormField()==false就不形成字段，
//              也就是其他的文件类型
                if(item.isFormField())
                {             //若表单项为普通表单项
                    String fileName = item.getFieldName();
                    String fileValue = item.getString("UTF-8");

                    System.out.println(fileName);
                    System.out.println(fileValue);
                }else
                {           //若表单项为文件表单项
                    String fileName = item.getName();
                    System.out.println(fileName);

                    InputStream is = item.getInputStream();
                    String path= this.getServletContext().getRealPath("/newFile");
                    System.out.println(path);
                    File descFile = new File(path,fileName);
                    OutputStream os = new FileOutputStream(descFile);
                    System.out.println("is="+is);
                    //输入流写入输出流
                    int len= -1;
                    byte[] buf = new byte[1024];
                    while ((len = is.read(buf))!=-1) {
                        os.write(buf,0,len);
                        result=1;
                    }
                    //关闭流
                    System.out.println("os="+os);
                    os.close();
                    is.close();


                }
            }


        }catch (Exception e)
        {
            System.out.println("upload—Servlet有误");
        }
        if(result==1)  //上传成功
        {
            response.sendRedirect("uploadSuccess.html");
        }else {
            response.sendRedirect("uploadFail.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

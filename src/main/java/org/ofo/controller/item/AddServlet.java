package org.ofo.controller.item;

import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.ofo.dao.ItemDao;
import org.ofo.entity.Item;
import org.ofo.resp.JsonResp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = "/item/add")
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码
        ItemDao itemDao = new ItemDao();
        Item data = new Item();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            // 创建一个基于磁盘的文件项工厂
            FileItemFactory factory = new DiskFileItemFactory();
            // 创建一个文件上传处理器
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            try {
                // 解析请求
                List<FileItem> items = upload.parseRequest(req);
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // 处理普通表单字段
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString("UTF-8");
                        System.out.println(fieldName);
                        System.out.println(fieldValue);
                        if (fieldName.equals("name")) {
                            data.setName(fieldValue);
                        }
                        if (fieldName.equals("price")) {
                            data.setPrice(new BigDecimal(fieldValue));
                        }
                        // 根据fieldName处理fieldValue，例如name和price
                    } else {
                        // 处理上传文件
                        String fieldName = item.getFieldName();
                        String fileName =  UUID.randomUUID().toString();
                        System.out.println(fieldName);
                        System.out.println(fileName);
                        String contentType = item.getContentType();
                        boolean isInMemory = item.isInMemory();
                        long sizeInBytes = item.getSize();

                        // 定义保存上传文件的目录
                        String uploadPath = getServletContext().getRealPath("") + File.separator + "upload";
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }
                        // 保存文件到服务器
                        item.write(new File(uploadPath + File.separator + fileName));

                        data.setImg("http://localhost:8080/upload/" + fileName);
                        // 在这里保存文件到服务器
                    }
                }
                itemDao.save(data);
                resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok()).getBytes(StandardCharsets.UTF_8)));
            } catch (FileUploadException e) {
                throw new ServletException("Cannot parse multipart request.", e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

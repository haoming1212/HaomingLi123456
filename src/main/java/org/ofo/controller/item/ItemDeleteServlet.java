package org.ofo.controller.item;

import com.alibaba.fastjson.JSON;
import org.ofo.dao.ItemDao;
import org.ofo.resp.JsonResp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/item/delete")
public class ItemDeleteServlet extends HttpServlet {


    private ItemDao itemDao = new ItemDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码
        String id = req.getParameter("id");
        itemDao.delete(id);
        resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok()).getBytes(StandardCharsets.UTF_8)));
    }
}

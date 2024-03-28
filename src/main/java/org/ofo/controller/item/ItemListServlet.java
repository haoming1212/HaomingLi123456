package org.ofo.controller.item;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import org.ofo.dao.ItemDao;
import org.ofo.entity.Item;
import org.ofo.resp.JsonResp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/item/list")
public class ItemListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码
        ItemDao itemDao = new ItemDao();
        List<Item> list = itemDao.list();
        String search = req.getParameter("search");
        if (!StringUtils.isEmptyOrWhitespaceOnly(search)) {
            list = list.stream().filter(x -> x.getName().contains(search)).collect(Collectors.toList());
        }
        resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok(list)).getBytes(StandardCharsets.UTF_8)));
    }
}

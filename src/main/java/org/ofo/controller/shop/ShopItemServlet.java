package org.ofo.controller.shop;

import com.alibaba.fastjson.JSON;
import org.ofo.dao.ShopDao;
import org.ofo.entity.Shop;
import org.ofo.resp.JsonResp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(urlPatterns = "/shop/item")
public class ShopItemServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码
        ShopDao shopDao = new ShopDao();
        String id = req.getParameter("id");
        List<Shop> shops = shopDao.list();
        Shop shop = null;
        for (Shop s : shops) {
            if (s.getUserId().equals(id)) {
                shop = s;
                break;
            }
        }
        resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok(shop)).getBytes(StandardCharsets.UTF_8)));
        resp.getWriter().flush();
    }
}

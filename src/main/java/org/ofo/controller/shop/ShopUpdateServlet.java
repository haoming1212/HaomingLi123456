package org.ofo.controller.shop;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
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


// 更新店铺信息
@WebServlet(urlPatterns = "/shop/update")
public class ShopUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码
        String id = req.getParameter("id");
        String userId = req.getParameter("userId");
        String name = req.getParameter("name");
        String manager = req.getParameter("manager");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        if (StringUtils.isEmptyOrWhitespaceOnly(id)) {
            ShopDao shopDao = new ShopDao();
            Shop shop = new Shop();
            shop.setUserId(userId);
            shop.setName(name);
            shop.setManager(manager);
            shop.setPhone(phone);
            shop.setAddress(address);
            shopDao.save(shop);
        } else {
            Shop shop = new ShopDao().getById(id);
            shop.setUserId(userId);
            shop.setName(name);
            shop.setManager(manager);
            shop.setPhone(phone);
            shop.setAddress(address);
            new ShopDao().update(shop);
        }
        resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok()).getBytes(StandardCharsets.UTF_8)));
        resp.getWriter().flush();
    }
}

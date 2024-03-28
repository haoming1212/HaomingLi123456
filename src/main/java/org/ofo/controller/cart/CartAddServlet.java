package org.ofo.controller.cart;

import com.alibaba.fastjson.JSON;
import org.ofo.dao.CartDao;
import org.ofo.dao.ItemDao;
import org.ofo.dao.UserDao;
import org.ofo.entity.Cart;
import org.ofo.entity.Item;
import org.ofo.entity.User;
import org.ofo.resp.JsonResp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/cart/add")
public class CartAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码

        UserDao userDao = new UserDao();

        String itemId = req.getParameter("itemId");
        String userId = req.getParameter("userId");

        // 已存在的情况
        Cart item = new CartDao().getByUserAndItem(userId, itemId);
        if (item != null) {
            item.setNumber(item.getNumber() + 1);
            new CartDao().update(item);
        } else {
            Item good = new ItemDao().getById(itemId);
            item = new Cart();
            item.setImg(good.getImg());
            item.setName(good.getName());
            item.setUnitPrice(good.getPrice());
            item.setNumber(1);
            item.setUserId(userId);
            item.setItemId(itemId);
            new CartDao().save(item);
        }

        resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok()).getBytes(StandardCharsets.UTF_8)));
        resp.getWriter().flush();
    }
}

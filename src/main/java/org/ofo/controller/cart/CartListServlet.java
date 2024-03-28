package org.ofo.controller.cart;

import com.alibaba.fastjson.JSON;
import org.ofo.dao.CartDao;
import org.ofo.entity.Cart;
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

@WebServlet("/cart/list")
public class CartListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码

        String userId = req.getParameter("id");
        List<Cart> list = new CartDao().list();
        List<Cart> filtered = list.stream().filter(x -> x.getUserId().equals(userId)).collect(Collectors.toList());
        resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok(filtered)).getBytes(StandardCharsets.UTF_8)));
        resp.getWriter().flush();
    }
}

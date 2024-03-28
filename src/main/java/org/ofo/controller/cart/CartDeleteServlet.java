package org.ofo.controller.cart;

import com.alibaba.fastjson.JSON;
import org.ofo.dao.CartDao;
import org.ofo.resp.JsonResp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/cart/delete")
public class CartDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cartId = req.getParameter("id");
        new CartDao().delete(cartId);
        resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok()).getBytes(StandardCharsets.UTF_8)));
        resp.getWriter().flush();
    }
}

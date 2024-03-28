package org.ofo.controller.order;

import com.alibaba.fastjson.JSON;
import org.apache.xpath.operations.Or;
import org.ofo.dao.*;
import org.ofo.entity.*;
import org.ofo.resp.JsonResp;
import org.ofo.util.IdGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/order/create")
public class PlaceOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OrderDao orderDao = new OrderDao();
        UserDao userDao = new UserDao();
        CartDao cartDao = new CartDao();
        CustomerDao customerDao = new CustomerDao();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码
        // 菜品id
        String userId = req.getParameter("userId");

        List<Cart> cartList = cartDao.list().stream().filter(x -> x.getUserId().equals(userId)).collect(Collectors.toList());


        Customer customer = customerDao.getByUserId(userId);


        // 计算总价
        BigDecimal money = BigDecimal.ZERO;
        for (Cart cart : cartList) {
            money = money.add(cart.getUnitPrice().multiply(BigDecimal.valueOf(cart.getNumber())));
        }

        // 保存订单信息
        Order order = new Order();
        order.setUserId(userId);
        if (customer != null) {
            order.setPhone(customer.getContact());
            order.setAddress(customer.getAddress());
            order.setReceiver(customer.getReceiver());
        }
        order.setId(IdGenerator.generate());
        order.setMoney(money);
        orderDao.save(order);

        // 保存订单明细
        for (Cart cart : cartList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setItemId(cart.getItemId());
            orderItem.setImg(cart.getImg());
            orderItem.setNumbers(cart.getNumber());
            orderItem.setUnitPrice(cart.getUnitPrice());
            orderItem.setName(cart.getName());
            new OrderItemDao().save(orderItem);
        }

        // 清空购物车
        for (Cart cart : cartList) {
            new CartDao().delete(cart.getId());
        }

        resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok()).getBytes(StandardCharsets.UTF_8)));
        resp.getWriter().flush();
    }
}

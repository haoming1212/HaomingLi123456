package org.ofo.controller.order;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.StringUtils;
import org.ofo.dao.OrderDao;
import org.ofo.dao.OrderItemDao;
import org.ofo.entity.Order;
import org.ofo.resp.JsonResp;
import org.ofo.resp.OrderResp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/order/list")
public class OrderListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码
        String userId = req.getParameter("userId");
        List<Order> list = new OrderDao().list();
        if (!StringUtils.isEmptyOrWhitespaceOnly(userId)) {
            list = list.stream().filter(x -> x.getUserId().equals(userId)).collect(Collectors.toList());
        }
        List<OrderResp> orderRespList = new ArrayList<>();
        for (Order order : list) {
            OrderResp orderResp = new OrderResp();
            orderResp.setOrder(order);
            orderResp.setOrderItems(new OrderItemDao().listByOrderId(order.getId()));
            orderRespList.add(orderResp);
        }

        resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok(orderRespList)).getBytes(StandardCharsets.UTF_8)));
        resp.getWriter().flush();

    }
}

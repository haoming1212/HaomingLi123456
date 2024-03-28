package org.ofo.controller.customer;

import com.alibaba.fastjson.JSON;
import org.ofo.dao.CustomerDao;
import org.ofo.entity.Customer;
import org.ofo.resp.JsonResp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/customer/add")
public class CustomerAdd extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码

        String address = req.getParameter("address");
        String contact = req.getParameter("contact");
        String userId = req.getParameter("userId");
        String receiver = req.getParameter("receiver");

        Customer customer = new CustomerDao().getByUserId(userId);
        if (customer != null) {
            customer.setReceiver(receiver);
            customer.setAddress(address);
            customer.setContact(contact);
            new CustomerDao().update(customer);
        } else {
            customer = new Customer();
            customer.setUserId(userId);
            customer.setReceiver(receiver);
            customer.setAddress(address);
            customer.setContact(contact);
            new CustomerDao().save(customer);
        }

        resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok()).getBytes(StandardCharsets.UTF_8)));
        resp.getWriter().flush();
    }
}

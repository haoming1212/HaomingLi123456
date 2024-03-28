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

@WebServlet("/customer/get")
public class CustomerGet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码

        String id = req.getParameter("id");
        Customer c = new CustomerDao().getByUserId(id);
        resp.getWriter().write(new String(JSON.toJSONString(JsonResp.ok(c)).getBytes(StandardCharsets.UTF_8)));
        resp.getWriter().flush();
    }
}

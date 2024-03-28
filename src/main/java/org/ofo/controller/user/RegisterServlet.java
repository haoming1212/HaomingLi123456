package org.ofo.controller.user;

import com.alibaba.fastjson.JSON;
import org.ofo.dao.UserDao;
import org.ofo.entity.User;
import org.ofo.resp.JsonResp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

// 注册
@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码
        UserDao userDao = new UserDao();
        String account = req.getParameter("account");
        System.out.println(account);
        String password = req.getParameter("password");
        List<User> list = userDao.list();
        for (User user : list) {
            if (account.equals(user.getAccount())
                && user.getRole().equals("user")) {
                JsonResp error = JsonResp.error("账号已存在");
                resp.getWriter().write(JSON.toJSONString(error));
                resp.getWriter().flush();
                return;
            }
        }

        userDao = new UserDao();
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setRole("user");
        userDao.save(user);
        JsonResp r = JsonResp.ok();
        resp.getWriter().write(JSON.toJSONString(r));
        resp.getWriter().flush();
    }
}
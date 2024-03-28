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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

// 登陆
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {


    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8"); // 明确告诉浏览器响应的编码
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        List<User> list = userDao.list();
        for (User user : list) {
            if (user.getAccount().equals(account)
                    && user.getPassword().equals(password)) {
                // 登陆成功
                JsonResp r = JsonResp.ok(user);
                resp.getWriter().write(JSON.toJSONString(r));
                resp.getWriter().flush();
                return;
            }
        }
        // 登陆失败

        JsonResp error = JsonResp.error("账号/密码错误");
        resp.getWriter().write(new String(JSON.toJSONString(error).getBytes(StandardCharsets.UTF_8)));
        resp.getWriter().flush();
    }
}

<%--
  Created by IntelliJ IDEA.
  User: zhangshuaiqiang
  Date: 2024/2/20
  Time: 00:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>online-food-order</title>
    <style>
        #form-div {
            margin-left: 30%;
            width: 40%;
            margin-top: 20%;
            background-color: rgba(0, 0, 0, 0.6);
            height: 32%;
            padding: 40px;
            color: white;
        }
    </style>
</head>
<body>
<div id="app">
    <div id="form-div">
        <div style="color: white; font-size: 20px; font-weight: bolder; text-align: center; margin: -20px 0 20px 0">
            ONLINE ORDERING SYSTEM
        </div>
        <div class="mb-3 row">
            <label for="account" class="col-sm-2 col-form-label">Account</label>
            <div class="col-sm-10">
                <input type="text"  class="form-control" id="account" value="">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password">
            </div>
        </div>
        <div class="mb-3 row" style="text-align: center">
            <div class="col">
                <button type="button" class="btn btn-primary mb-3" onclick="register()">Register</button>
            </div>

            <div class="col">
                <button type="button" class="btn btn-info mb-3" onclick="toLogin()">To Login</button>
            </div>
        </div>
    </div>
</div>
</body>

<script>

    function toLogin(){
        location.href = 'login.jsp'
    }

    function register(){
        const account = $("#account").val()
        const password = $("#password").val()
        if (!account) {
            alert('账号不能为空')
            return
        }
        if (!password) {
            alert('密码不能为空')
            return
        }

        let formData = new URLSearchParams();
        formData.append('account', account);
        formData.append('password', password);

        fetch('/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                console.log(data)
                if (data.code === 200) {
                    location.href = 'login.jsp'
                } else {
                    alert(data.msg)
                }
            })
            .catch(error => console.error('Error:', error));
    }
</script>

</html>

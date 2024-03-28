<%--
  Created by IntelliJ IDEA.
  User: zhangshuaiqiang
  Date: 2024/3/10
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title></title>
    <style>
        .rows {
            display: flex;
            align-items: center; /* 垂直居中 */
            height: 100px; /* 为了示例，你可以根据需要调整高度 */
        }

        /* 使按钮在.col内水平居中 */
        .cols {
            display: flex;
            justify-content: center; /* 水平居中 */
            color: white;
            height: 100px;
            text-align: center;
            line-height: 100px;
        }

        .active {
            color: pink;
            background-color: white;
        }
    </style>
</head>
<body>
    <div class="header" style=" background-color: pink;">
        <div class="container text-center">
            <div class="row rows">
                <div class="col cols active" style="text-align: center;">
                    Shop Information
                </div>
                <div class="col cols" onclick="navigatePage(2)">
                    Items List
                </div>
                <div class="col cols" onclick="navigatePage(3)">
                    Order List
                </div>
                <div class="col cols" onclick="navigatePage(4)">
                    My Income
                </div>
            </div>
        </div>

    </div>
    <div class="main-content" style="padding: 20px; height:calc(100vh - 100px); background-color: #f2f4f6">
        <div style="background-color: #ffffff; height:calc(100vh - 140px); padding: 100px">
            <div class="mb-3">
                <label for="name" class="form-label">Shop Name</label>
                <input type="text" class="form-control" id="name" placeholder="">
            </div>
            <div class="mb-3">
                <label for="manager" class="form-label">Manager</label>
                <input type="text" class="form-control" id="manager" placeholder="">
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Contact</label>
                <input type="text" class="form-control" id="phone" placeholder="">
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <textarea class="form-control" id="address" rows="3"></textarea>
            </div>

            <div style="margin-top: 10px; text-align: center">
                <div class="row">
                    <div class="col">
                        <button type="button" class="btn btn-primary" onclick="submitInfo()">Submit</button>
                    </div>
                    <div class="col">
                        <button type="button" class="btn btn-danger" onclick="logout()">Logout</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>

    window.onload = function () {
        const userJson = sessionStorage.getItem('user')
        if (!userJson) {
            location.href = '../login.jsp'
            return
        }
        const user = JSON.parse(userJson)
        let formData = new URLSearchParams();
        formData.append('id', user.id);
        fetch('/shop/item', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    const shopData = data.data
                    if (shopData) {
                        $("#name").val(shopData.name)
                        $("#manager").val(shopData.manager)
                        $("#phone").val(shopData.phone)
                        $("#address").val(shopData.address)
                    }
                }
            })
            .catch(error => console.error('Error:', error));

    }

    function navigatePage(val){
        if (val === 2) {
            location.href = 'menu.jsp'
        } else if (val === 3) {
            location.href = 'order.jsp'
        } else if (val === 4) {
            location.href = 'income.jsp'
        }
    }

    function logout(){
        sessionStorage.clear()
        location.href = '../login.jsp'
    }

    function submitInfo() {
        const userJson = sessionStorage.getItem('user')
        const user = JSON.parse(userJson)
        const name = $("#name").val()
        const manager = $("#manager").val()
        const phone = $("#phone").val()
        const address = $("#address").val()
        if (!name) {
            alert("Please Input Name")
            return
        }
        if (!manager) {
            alert("Please Input Manager")
            return
        }
        if (!phone) {
            alert("Please Input Contact")
            return
        }
        if (!address) {
            alert("Please Input Address")
            return
        }

        let formData = new URLSearchParams();
        formData.append('name', name);
        formData.append("userId", user.id)
        formData.append('manager', manager);
        formData.append('phone', phone);
        formData.append('address', address);

        fetch('/shop/update', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                console.log(data)
                if (data.code === 200) {
                } else {
                    alert(data.msg)
                }
            })
            .catch(error => console.error('Error:', error));
    }
</script>
</html>

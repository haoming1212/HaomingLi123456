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
        .row {
            display: flex;
            align-items: center; /* 垂直居中 */
            height: 100px; /* 为了示例，你可以根据需要调整高度 */
        }

        /* 使按钮在.col内水平居中 */
        .col {
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
        <div class="row">
            <div class="col" style="text-align: center;" onclick="navigatePage(1)">
                Shop Information
            </div>
            <div class="col" onclick="navigatePage(2)">
                Items List
            </div>
            <div class="col" onclick="navigatePage(3)">
                Order List
            </div>
            <div class="col active">
                My Income
            </div>
        </div>
    </div>
</div>


<div class="main-content" style="padding: 20px; height:calc(100vh - 100px); background-color: #f2f4f6">
    <div  style="background-color: #ffffff; height:calc(100vh - 240px);   padding: 100px; text-align: center; font-size: 4rem">
        <div id="income">

        </div>
    </div>
</div>

<script>
    function navigatePage(val){
        if (val === 1) {
            location.href = 'shop.jsp'
        } else if (val === 2) {
            location.href = 'menu.jsp'
        } else if (val === 3) {
            location.href = 'order.jsp'
        }
    }

    window.onload = function () {
        queryOrder()
    }

    function queryOrder(){
        const userJson = sessionStorage.getItem('user')
        if (!userJson) {
            location.href = '../login.jsp'
            return
        }
        fetch('/order/list', {
            method: 'GET',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        }).then(response => response.json())
            .then(data => {
                if (data.code === 200) {
                    const orderDataList = data.data
                    let totalMoney = 0
                    for (let i = 0; i < orderDataList.length; i++) {
                        const orderItem = orderDataList[i]
                        const orderData = orderItem.order
                        totalMoney = totalMoney + orderData.money
                    }
                    $("#income").html("You  Total  Income  is   <font color='red'>" + totalMoney + "</font>   RMB")
                }
            })
            .catch(error => console.error('Error:', error));
    }
</script>
</body>
</html>

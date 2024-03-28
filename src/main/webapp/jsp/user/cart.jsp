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
        <div class="row rows" style="display: flex;
            align-items: center; /* 垂直居中 */
            height: 100px; /* 为了示例，你可以根据需要调整高度 */">
            <div class="col cols"  onclick="navigatePage(1)">
                MENU
            </div>
            <div class="col cols active" style="text-align: center;">
                CART
            </div>
            <div class="col cols" onclick="navigatePage(3)">
                MY ORDER
            </div>
            <div class="col cols"  onclick="navigatePage(4)">
                USER CENTER
            </div>
        </div>
    </div>
</div>

<div class="main-content" style="padding: 20px; height: max-content; background-color: #f2f4f6">

    <div style="background-color: #ffffff; height:max-content; padding: 20px">
        <div class="row">
            <div class="col">
                Item
            </div>
            <div class="col">
                Unit Price
            </div>
            <div class="col">
                Image
            </div>
            <div class="col">
                Count
            </div>
            <div class="col">
                Total Price
            </div>
            <div class="col">
                Operations
            </div>
        </div>
        <div class="cart-list">

        </div>
        <div class="calculate" style="margin: 30px 0; text-align: center">
            <div class="row">
                <div class="col" id="totalPrice">

                </div>
                <div class="col">
                    <button type="button" class="btn btn-primary" onclick="calculateCart()">Calculate</button>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
<script>

    function navigatePage(val){
        if (val === 1) {
            location.href = 'menu.jsp'
        } else if (val === 4) {
            location.href = 'profile.jsp'
        } else if (val === 3) {
            location.href = 'order.jsp'
        }
    }

    window.onload = function () {
        queryCart()
    }

    function queryCart(){
        const user = sessionStorage.getItem("user");
        const userId = JSON.parse(user).id
        fetch('/cart/list?id=' + userId, {
            method: 'GET'
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // 或者使用response.text()如果响应是文本
        }).then(data => {
            const dataList = data.data
            console.log(dataList)
            $(".cart-list").empty();
            let totalMoney = 0;
            for (let i = 0; i < dataList.length; i++) {
                const item = dataList[i]
                totalMoney += item.number * item.unitPrice
                const content = `
                     <div class="row" style="margin-top: 20px">
                        <div class="col">
                            ` + item.name + `
                        </div>
                        <div class="col">
                            ` + item.unitPrice + `
                        </div>
                        <div class="col">
                           <img height="100px" width="100px" src="` + item.img + `"/>
                        </div>
                        <div class="col">
                            ` + item.number + `
                        </div>
                        <div class="col">
                            ` + item.number * item.unitPrice + `
                        </div>
                        <div class="col">
                            <button type="button" class="btn btn-danger" onclick="deleteCart('` + item.id + `')">Delete</button>
                        </div>
                    </div>
                `;
                $(".cart-list").append(content)
                $("#totalPrice").html( "Total: <font color='red'>" + totalMoney + " </font> RMB")
            }
        })
    }



    function deleteCart(id) {
        fetch('/cart/delete?id=' + id, {
            method: 'GET'
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // 或者使用response.text()如果响应是文本
        }).then(data => {
            location.reload()
        })
    }


    // 结算购物车
    function calculateCart(){

        const user = sessionStorage.getItem("user");
        const userId = JSON.parse(user).id
        let formData = new URLSearchParams();
        formData.append('userId', userId);
        fetch('/order/create', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: formData
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // 或者使用response.text()如果响应是文本
            })
            .then(data => {
                console.log(data)
                if (data.code === 200) {
                    location.reload()
                }
            })
            .catch(error => console.error('Error:', error));
    }
</script>
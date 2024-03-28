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
            align-items: center;
            height: 100px;
        }

        .cols {
            display: flex;
            justify-content: center;
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
            <div class="col cols active"  style="text-align: center;">
                MENU
            </div>
            <div class="col cols" onclick="navigatePage(2)">
                CART
            </div>
            <div class="col cols" onclick="navigatePage(3)">
                MY ORDER
            </div>
            <div class="col cols" onclick="navigatePage(4)">
                USER CENTER
            </div>
        </div>
    </div>
</div>
<div class="main-content" style="padding: 20px; height: max-content; background-color: #f2f4f6">

    <div style="background-color: #ffffff; height:max-content; padding: 20px">
        <div style="margin: 10px">
            <div class="input-group mb-3">
                <input type="text" id="searchVal" class="form-control" placeholder="Input Item Name For Searching">
                <button class="btn btn-outline-success" type="button" id="button-addon2" onclick="searchData()">Search Item</button>
            </div>
        </div>
        <div style="display: flex; flex-wrap:wrap" id="item-list">
        </div>

    </div>
</div>

</body>
<script>

    function addToCart(id) {
        const user = sessionStorage.getItem("user");
        const userId = JSON.parse(user).id

        let formData = new URLSearchParams();
        formData.append('itemId', id);
        formData.append('userId', userId);
        console.log(id)
        fetch('/cart/add', {
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

                }
            })
            .catch(error => console.error('Error:', error));
    }


    function searchData(){
        listItems()
    }

    function listItems(){
        fetch('/item/list?search=' + $("#searchVal").val(), {
            method: 'GET'
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // 或者使用response.text()如果响应是文本
        }).then(data => {
            console.log(data)
            const dataList = data.data
            $("#item-list").empty();
            for (let i = 0; i < dataList.length; i++) {
                const item = dataList[i]
                const content = `
                    <div class="card" style="width: 11rem; margin:  1rem">
                        <img height="150px" src="` + item.img + `" class="card-img-top" alt="">
                        <div class="card-body">
                            <p class="card-text">` + item.name + `</p>
                            <div class="row">
                                <div class="col">
                                                <p class="card-text">¥ ` + item.price + `</p>
                                </div>
                                <div class="col">
                                           <button type="button" class="btn btn-primary" onclick="addToCart('` + item.id + `')"> + </button>
                                </div>
                            </div>

                        </div>
                    </div>
                `;
                $("#item-list").append(content)
            }
        })
    }

    window.onload = function () {
        listItems()
    }

    function navigatePage(val){
        if (val === 2) {
            location.href = 'cart.jsp'
        } else if (val === 3) {
            location.href = 'order.jsp'
        } else if (val === 4) {
            location.href = 'profile.jsp'
        }
    }
</script>
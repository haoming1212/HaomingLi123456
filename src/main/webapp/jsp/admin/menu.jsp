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
                Shop Information
            </div>
            <div class="col cols active" style="text-align: center;">
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

<div class="main-content" style="padding: 20px; height: max-content; background-color: #f2f4f6">

    <div style="background-color: #ffffff; height:max-content; padding: 20px">
        <div style="margin: 10px">
            <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"> Add Item </button>
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Add Item</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form id="myForm" enctype="multipart/form-data">
                                <div class="mb-3">
                                    <label for="name" class="col-form-label">Item Name:</label>
                                    <input type="text" class="form-control" name="name" id="name">
                                </div>
                                <div class="mb-3">
                                    <label for="price" class="col-form-label">Item Price:</label>
                                    <input class="form-control" type="number" name="price" id="price"></input>
                                </div>
                                <div class="mb-3">
                                    <label for="img" class="col-form-label">Item Image:</label>
                                    <input type="file" accept="image/*" name="img" class="form-control" id="img"></input>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" onclick="submitItem()">Submit Data</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="display: flex; flex-wrap:wrap" id="item-list">
        </div>

    </div>
</div>

<script>



    function listItems(){
        fetch('/item/list', {
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
                                           <button type="button" class="btn btn-danger" onclick="deleteItem('` + item.id + `')">Delete</button>
                                </div>
                            </div>

                        </div>
                    </div>
                `;
                $("#item-list").append(content)
            }
        })
    }


    function deleteItem(id) {
        console.log(id)
        fetch('/item/delete?id=' + id, {
            method: 'GET',
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

    function navigatePage(val){
        if (val === 1) {
            location.href = 'shop.jsp'
        } else if (val === 3) {
            location.href = 'order.jsp'
        } else if (val === 4) {
            location.href = 'income.jsp'
        }
    }

    window.onload = function () {
        listItems()
    }


    // 提交菜品
    function submitItem(){
        const form = document.getElementById('myForm');
        const formData = new FormData(form);
        console.log(formData)
        fetch('/item/add', {
            method: 'POST',
            body: formData, // FormData对象作为请求体
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
</body>
</html>

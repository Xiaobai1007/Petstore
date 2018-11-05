<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<input type="text" id = userName name="userName">
<input type="password" id="userPassword" name="userPassword">
<button name="login" id="login">登录</button>

<script src="/js/jquery-3.3.1.min.js"></script>
<script>
    $("#login").click(function () {

        var userList = new Array();
        userList.push({
            "userId": "",
            "userName": $("#userName").val(),
            "userFirstName": "",
            "userLastName": "",
            "userEmail": "",
            "userPassword": $("#userPassword").val(),
            "userPhone": "",
            "userStatus": ""
        });

        alert("1231231")

        $.ajax({
            url: "/user/login",
            type: "get",
            contentType:"application/json;charset=utf-8",
            data: JSON.stringify(userList),
            success: function (data) {
                console.log(data)
                alert(data.msg)
            }
        });

    })
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<label for="userName">用户名:</label><input type="text" id=userName name="userName"><br><br>
<label for="userPassword">密　码:</label><input type="password" id="userPassword" name="userPassword"><br>
<button name="login" id="login">登录</button>

<button id="btn" onclick="fasong()">提交</button>

<script src="/js/jquery-3.3.1.min.js"></script>
<script>

    const url = 'http://localhost:8080';

    function loadScript(src) {
        const script = document.createElement('script');
        script.src = src;
        document.body.appendChild(script);
    }

    function fasong() {
        loadScript(`${url}/user/cors?callback=aaa`)
        // fetch(url + "/user/cors?callback=aaa", {
        //     method: 'get',
        // }).then(resp => resp.text())
        //     .then(console.log);
    }

    function aaa(c) {
        alert(c);
    }

    $("#login").click(function () {

        $.ajax({
            url: "/user/login",
            type: "get",
            data: {"userName": $("#userName").val(), "userPassword": $("#userPassword").val()},
            success: function (data) {
                if (data.msg == "success") {
                    alert("登录成功");
                    window.location.href = "/user/success";
                } else {
                    alert("密码或用户名错误");
                }
            }
        });

    })
</script>
</body>
</html>

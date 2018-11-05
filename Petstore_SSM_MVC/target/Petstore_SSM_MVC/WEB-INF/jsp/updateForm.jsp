<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户修改</title>
</head>
<body>
<label for="userId">用户编号:</label><input type="text" readonly name="userId" id="userId" value="${userId}"><br>
<label for="userName">用户名　:</label><input type="text" name="userName" id="userName"><br>
<label for="userLastName">用户姓氏:</label><input type="text" name="userLastName" id="userLastName"><br>
<label for="userFirstName">用户名字:</label><input type="text" name="userFirstName" id="userFirstName"><br>
<label for="userEmail">邮　　箱:</label><input type="text" name="userEmail" id="userEmail"><br>
<label for="userPassword">密　　码:</label><input type="text" name="userPassword" id="userPassword"><br>
<label for="userPhone">联系电话:</label><input type="text" name="userPhone" id="userPhone"><br>
用户状态:<input type="radio" name="userStatus" id="zc" value="0">正常&nbsp;
<input type="radio" name="userStatus" id="dj" value="1">冻结
<input type="button" id="updateBtn" value="确认修改">

<script src="/js/jquery-3.3.1.min.js"></script>
<script>
    var list = function () {
        $.ajax({
            url:"/user/finByIdUser",
            type:"get",
            data:{"userId":$("#userId").val()},
            success:function (data) {
                console.log(data)
                $("#userId").val(data.userId)
                $("#userName").val(data.userName)
                $("#userFirstName").val(data.userFirstName)
                $("#userLastName").val(data.userLastName)
                $("#userEmail").val(data.userEmail)
                $("#userPassword").val(data.userPassword)
                $("#userPhone").val(data.userPhone)
                if(data.userStatus == 0)
                    $("#zc").prop("checked","true");
                else
                    $("#dj").prop("checked","true");
            }
        })
        $("#updateBtn").click(function () {
            if(window.confirm("是否确认修改？")){
                $.ajax({
                    url:"/user/updateUser",
                    type:"post",
                    data:{"userId":$("#userId").val(),"userName":$("#userName").val(),"userFirstName":$("#userFirstName").val(),"userLastName":$("#userLastName").val(),"userEmail":$("#userEmail").val(),"userPassword":$("#userPassword").val(),"userPhone":$("#userPhone").val(),"userStatus":$("input[name=userStatus]:checked").prop("value")},
                    success:function (data) {
                        alert(data.msg)
                        window.location.href = "/user/success";
                    }
                })
            }else{
                return false;
            }
        })
    }

    list();
</script>
</body>
</html>

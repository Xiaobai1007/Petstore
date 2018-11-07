<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>用户中心</h2>
<a href="/pet">宠物商城</a>&nbsp;&nbsp;<a href="/user/logout">注销</a><br><br>
<a href="/user/insertForm">添加用户</a><br><br>

<input type="text" name="selectName" id="selectName" style="width:300px;margin-bottom: 5px">&nbsp;<input type="button" value="搜索" id="selectBtn">
<table id="userList" border="1" cellspacing="0" style="width:80%;text-align: center;">
    <tr>
        <th>编号</th>
        <th>用户名</th>
        <th>用户名称</th>
        <th>邮箱</th>
        <th>密码</th>
        <th>电话</th>
        <th>状态</th>
        <th>操作</th>
    </tr>

    <tbody id="tbody"></tbody>
</table>

<script src="/js/jquery-3.3.1.min.js"></script>
<script>
    var list = function () {
        $.ajax({
            url:"/user/findAllUser",
            type:"get",
            success:function (data) {
                var tbody = $("#tbody");
                $.each(data,function (index,obj) {
                    if(obj.userStatus == 0)
                        tbody.append("<tr><td>"+obj.userId+"</td><td>"+obj.userName+"</td><td>"+obj.userLastName+obj.userFirstName+"</td><td>"+obj.userEmail+"</td><td>"+obj.userPassword+"</td><td>"+obj.userPhone+"</td><td>正常</td><td><a href='/user/updateForm?userId="+obj.userId+"'>修改</a>&nbsp;<a href='#' value= '"+obj.userId+"' class='delUser'>删除</a></td></tr>")
                    else
                        tbody.append("<tr><td>"+obj.userId+"</td><td>"+obj.userName+"</td><td>"+obj.userLastName+obj.userFirstName+"</td><td>"+obj.userEmail+"</td><td>"+obj.userPassword+"</td><td>"+obj.userPhone+"</td><td>冻结</td><td><a href='/user/updateForm?userId="+obj.userId+"'>修改</a>&nbsp;<a href='#' value= '"+obj.userId+"' class='delUser'>删除</a></td></tr>")
                })
            }
        })
    }
    list();

    $("#tbody").on("click",".delUser",function () {
        var tr = $(this).parent().parent();
        if (window.confirm("是否确认删除？")) {
            $.ajax({
                url: "/user/deleteUser",
                type: "get",
                data: {"userId": $(this).attr("value")},
                success: function (data) {
                    alert(data.msg)
                    tr.remove();
                }
            })
        } else {
            return false;
        }
    })

    $("#selectBtn").click(function () {
        $.ajax({
            url:"/user/findByUserName",
            type:"get",
            data:{"userName":$("#selectName").val()},
            success:function (data) {
                var tbody = $("#tbody");
                $("#tbody tr").remove()
                $.each(data,function (index,obj) {
                    if(obj.userStatus == 0)
                        tbody.append("<tr><td>"+obj.userId+"</td><td>"+obj.userName+"</td><td>"+obj.userLastName+obj.userFirstName+"</td><td>"+obj.userEmail+"</td><td>"+obj.userPassword+"</td><td>"+obj.userPhone+"</td><td>正常</td><td><a href='/user/updateForm?userId="+obj.userId+"'>修改</a>&nbsp;<a href='#' value= '"+obj.userId+"' class='delUser'>删除</a></td></tr>")
                    else
                        tbody.append("<tr><td>"+obj.userId+"</td><td>"+obj.userName+"</td><td>"+obj.userLastName+obj.userFirstName+"</td><td>"+obj.userEmail+"</td><td>"+obj.userPassword+"</td><td>"+obj.userPhone+"</td><td>冻结</td><td><a href='/user/updateForm?userId="+obj.userId+"'>修改</a>&nbsp;<a href='#' value= '"+obj.userId+"' class='delUser'>删除</a></td></tr>")
                })
            }
        })
    })

</script>
</body>
</html>

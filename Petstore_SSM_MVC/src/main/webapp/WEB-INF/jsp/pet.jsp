<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宠物商城</title>
</head>
<body>
<h2>欢迎${sessionScope.user},来到宠物商城</h2>
<p><a href="/pet/insertPetForm">添加宠物</a></p>
<input type="text" name="selectPetId" id="selectPetId" style="width:300px;margin-bottom: 5px">&nbsp;
<input type="button" value="搜索" id="selectBtn"><br>
<div id="radioDiv">
    <input type="radio" name="petStatus" checked="true" class="petStatus" value="">全部&nbsp;
    <input type="radio" name="petStatus" class="petStatus" class="petStatus" value="可用">可用&nbsp;
    <input type="radio" name="petStatus" class="petStatus" value="待定">待定&nbsp;
    <input type="radio" name="petStatus" class="petStatus" value="已售出">已售出&nbsp;
</div>
<table border="1" cellspacing="0" width="50%" style="text-align: center">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>品种</th>
        <th>价格</th>
        <th>图片</th>
        <th>性格</th>
        <th>状态</th>
        <th>修改</th>
    </tr>
    <tbody id="tbody"></tbody>
</table>

<script src="/js/jquery-3.3.1.min.js"></script>
<script>
    var list = function () {
        $.ajax({
            url: "/pet/selectPetAll",
            type: "get",
            success: function (data) {
                var tbody = $("#tbody");
                $.each(data, function (index, obj) {
                    tbody.append("<tr><td>" + obj.petId + "</td><td>" + obj.petName + "</td><td>" + obj.categoryInfo.categoryName + "</td><td>" + obj.petPrice + "</td><td><img src='/images/" + obj.petPhoto + "' style='width: 5em;height: 3em;'/></td><td>" + obj.petTag + "</td><td>" + obj.petStatus + "</td><td><a href='/pet/updatePetForm?petId="+obj.petId+"'>修改</a>&nbsp;<a href='#' class='delPet' value=" + obj.petId + ">删除</a></td></tr>")
                })
            }
        })
    }
    list();

    $("#tbody").on("click", ".delPet", function () {
        var tr = $(this).parent().parent();
        if (window.confirm("是否确认删除？")) {
            $.ajax({
                url: "/pet/deletePet",
                type: "post",
                data: {"petId": $(this).attr("value")},
                success: function (data) {
                    alert(data.msg)
                    tr.remove();
                }
            })
        } else {
            return false;
        }
    })

    $("#radioDiv").on("click", ".petStatus", function () {
        $.ajax({
            url: "/pet/selectByPetStatus",
            type: "get",
            data: {"petStatus": $(this).prop("value")},
            success: function (data) {
                $("#tbody tr").remove()
                var tbody = $("#tbody");
                $.each(data, function (index, obj) {
                    tbody.append("<tr><td>" + obj.petId + "</td><td>" + obj.petName + "</td><td>" + obj.categoryInfo.categoryName + "</td><td>" + obj.petPrice + "</td><td><img src='/images/" + obj.petPhoto + "' style='width: 5em;height: 3em;'/></td><td>" + obj.petTag + "</td><td>" + obj.petStatus + "</td><td><a href='/pet/updatePetForm?petId="+obj.petId+"'>修改</a>&nbsp;<a href='#' class='delPet' value=" + obj.petId + ">删除</a></td></tr>")
                })
            }
        })
    })

    $("#selectBtn").click(function () {
        $.ajax({
            url:"/pet/selectByPetId",
            type:"get",
            data:{"petId":$("#selectPetId").val()},
            success:function (data) {
                var tbody = $("#tbody");
                $("#tbody tr").remove()
                $.each(data,function (index,obj) {
                    tbody.append("<tr><td>" + obj.petId + "</td><td>" + obj.petName + "</td><td>" + obj.categoryInfo.categoryName + "</td><td>" + obj.petPrice + "</td><td><img src='/images/" + obj.petPhoto + "' style='width: 5em;height: 3em;'/></td><td>" + obj.petTag + "</td><td>" + obj.petStatus + "</td><td><a href='/pet/updatePetForm?petId="+obj.petId+"'>修改</a>&nbsp;<a href='#' class='delPet' value=" + obj.petId + ">删除</a></td></tr>")
                })
            }
        })
    })
</script>
</body>
</html>

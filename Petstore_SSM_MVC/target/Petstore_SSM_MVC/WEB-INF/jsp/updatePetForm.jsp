<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<label for="petId">宠物编号:</label> <input type="text" readonly id="petId" name="petId" value="${petId}"><br><br>
<label for="petName">宠物名称:</label> <input type="text" id="petName" name="petName"><br><br>
<label for="petPrice">宠物价格:</label> <input type="text" id="petPrice" name="petPrice"><br><br>
<label>宠物品种:</label>
<select id="categoryName">
    <option>-请选择-</option>
</select><br><br>
<label for="petTag">宠物性格:</label> <input type="text" id="petTag" name="petTag"><br><br>
<label>售卖状态:</label><input type="radio" name="petStatus" value="可用" id="ky">可用&nbsp;<input type="radio" name="petStatus"
                                                                                           value="待定"
                                                                                           id="dd">待定&nbsp;<input
        type="radio" name="petStatus" value="已售出" id="ysc">已售出<br><br>
<div style="color: red" id="err">
    ${err}
</div>
<label>宠物图片:</label><br>
<img src="" alt="宠物照片" id="petPhoto" style="width: 300px;height: 200px"><br><br>
<label>上传图片:</label>
<input type="hidden" id="img">
<form action="#" id="uploadPic" method="post" enctype="multipart/form-data">
    <input type="file" name="multipartFile"/>
    <input type="button" id="imgInput" value="上传">
</form>
<input type="button" id="updatePetBtn" value="确认修改"/>


<script src="/js/jquery-3.3.1.min.js"></script>
<script>
    var list = function () {
        //获取所有种类
        $.ajax({
            url: "/category",
            type: "get",
            success: function (data) {
                $.each(data, function (index, obj) {
                    $("#categoryName").append("<option value='"+obj.categoryId+"'>" + obj.categoryName + "</option>")
                })
            }
        })
        //获取宠物相关信息
        $.ajax({
            url: "/pet/selectByPetId",
            type: "get",
            data: {"petId": $("#petId").val()},
            success: function (data) {
                $.each(data, function (index, obj) {
                    $("#petName").val(obj.petName)
                    $("#petPrice").val(obj.petPrice)
                    $("#categoryName").val(obj.categoryInfo.categoryId)
                    $("#petTag").val(obj.petTag)
                    if (obj.petStatus === "可用") {
                        $("#ky").prop("checked", "true")
                    } else if (obj.petStatus === "待定") {
                        $("#dd").prop("checked", "true")
                    } else if (obj.petStatus === "已售出") {
                        $("#ysc").prop("checked", "true")
                    }
                    $("#petPhoto").attr("src", "/images/" + obj.petPhoto)
                    $("#img").val(obj.petPhoto)
                })
            }
        })
    }
    list();
    //上传图片
    $("#imgInput").click(function () {
        var formData = new FormData($("#uploadPic")[0]);
        $.ajax({
            type: "POST",
            url: "/pet/input",
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                $("#img").val(data.img)
                $("#petPhoto").attr("src", "/images/" + data.img)
            },
            error: function (data) {
                alert("error:");
            }
        });
    })

    //修改宠物信息
    $("#updatePetBtn").click(function(){
        if (window.confirm("是否确认修改？")) {
            $.ajax({
                url: "/pet/updatePet",
                type: "post",
                data: {
                    "petId": $("#petId").val(),
                    "petName": $("#petName").val(),
                    "petPrice": $("#petPrice").val(),
                    "categoryId": $("#categoryName").val(),
                    "petTag": $("#petTag").val(),
                    "petStatus": $("input[name=petStatus]:checked").prop("value"),
                    "petPhoto": $("#img").val()
                },
                success: function (data) {
                    alert(data.msg)
                    window.location.href = "/pet";
                }
            })
        } else{
            return false;
        }
    })
</script>
</body>
</html>

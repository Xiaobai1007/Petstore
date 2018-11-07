<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<label for="petName">宠物名称:</label> <input type="text" id="petName" name="petName"><br><br>
<label for="petPrice">宠物价格:</label> <input type="text" id="petPrice" name="petPrice"><br><br>
<label>宠物品种:</label> <select id="categoryName">
    <option>-请选择-</option>
</select><br><br>
<label for="petTag">宠物性格:</label> <input type="text" id="petTag" name="petTag"><br><br>
<label>售卖状态:</label>
<input type="radio" name="petStatus" value="可用" checked="true" id="ky">可用&nbsp;
<input type="radio" name="petStatus" value="待定" id="dd">待定&nbsp;
<input type="radio" name="petStatus" value="已售出" id="ysc">已售出<br><br>
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
<input type="button" id="insertPetBtn" value="确认添加"/>


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

    //添加宠物信息
    $("#insertPetBtn").click(function () {
        if (window.confirm("是否确认添加？")) {
            $.ajax({
                url: "/pet/insertPet",
                type: "post",
                data: {
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
        } else {
            return false;
        }
    })

</script>
</body>
</html>

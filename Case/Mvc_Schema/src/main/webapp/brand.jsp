<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>品牌案例</title>
</head>
<body>


<input type="button" value="新增" id="add_id"><br>
<hr>
<table border="1" cellspacing="0" width="800">
    <tr>
        <th>序号</th>
        <th>品牌名称</th>
        <th>企业名称</th>
        <th>排序</th>
        <th>品牌介绍</th>
        <th>状态</th>
        <th>操作</th>
    </tr>


    <c:forEach items="${brands}" var="brand">
        <tr align="center">
            <td>${brand.id}</td>
            <td>${brand.brandName}</td>
            <td>${brand.companyName}</td>
            <td>${brand.ordered}</td>
            <td>${brand.description}</td>
            <c:if test="${brand.status == 1 }">
                <td>开启</td>
            </c:if>
            <c:if test="${brand.status != 1}">
                <td>禁止</td>
            </c:if>
            <td><a href="/web/update_show?id=${brand.id}">修改</a> <a href="/web/delete_servlet?id=${brand.id}">删除</a></td>
        </tr>
    </c:forEach>
</table>

<script>
    //获取新增按钮的元素
    var eleAdd = document.getElementById("add_id");
    /* 添加点击事件*/
    eleAdd.onclick = function () {
        location.href = "/web/add_brand.jsp";
    }

</script>

</body>
</html>

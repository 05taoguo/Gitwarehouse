<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2023/6/11
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="1" cellspacing="0" width="100%">
    <tr align="center">
        <th colspan="7">课程表</th>
    </tr>

    <c:forEach items="${}" var="course">
        <tr align="center">
            <td>${course.course1}</td>
            <td>${course.course2}</td>
            <td>${course.course3}</td>
            <td>${course.course4}</td>
            <td>${course.course5}</td>
        </tr>

    </c:forEach>

</table>

</body>
</html>

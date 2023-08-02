<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2023/6/13
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>login</title>
  <link href="css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
  <form action="/web/login" id="form" method="post">
    <h1 id="loginMsg">LOGIN IN</h1>

    <div id="errorMsg">${errorMsg}</div>

    <p>Username:<input id="username" name="username" value="${cookie.c_username.value}" type="text"></p>

    <p>Password:<input id="password" name="password" value="${cookie.c_password.value}" type="password"></p>
    <p>Remember:<input id="remember" name="remember" value="1" type="checkbox"></p>
    <div id="subDiv">
      <input type="submit" class="button" value="login up">
      <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
      <a href="register.jsp">没有账号？</a>
    </div>
  </form>
</div>

<script>

</script>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2023/6/14
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="/web/register" method="get">

        <table>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="username" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg">${register_arr}</span>
                </td>

            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">请输入小于6位数</span>
                </td>
            </tr>

            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode">
                    <img src="/web/checkCodeServlet" id="src_a">
                    <a id="changeImg">看不清？</a>
                    <p><span id="check_code_err_msg" style="color: red;font-size: 14px">${check_code_err_msg}</span></p>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>

<script>
    var elementById = document.getElementById("changeImg");
    elementById.onclick = function () {
        let elementById1 = document.getElementById("src_a");
        let milliseconds2 = new Date().getMilliseconds();
        elementById1.src = "/web/CodeGenServlet?date" + milliseconds2;
    }

    var elementById2 = document.getElementById("src_a");
    elementById2.onclick = function () {
        let milliseconds3 = new Date().getMilliseconds();
        elementById2.src = "/web/CodeGenServlet?date" + milliseconds3;
    }
</script>
<%--<script>
    //判断密码是否是6位数
    var passwordEle = document.getElementById("password");
    passwordEle.onblur = function () {

        //获取密码的值
        var password = passwordEle.value

        // 1.1:创建对象ajax的对象
        var xhttp = new XMLHttpRequest();

        // 1.2:发送请求
        xhttp.open("GET", "http://localhost:8080/web/SelectPasswordServlet?password=" + password);
        xhttp.send();

        // 1.3:接收响应
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var resp = this.responseText;
                var passwordErrEle = document.getElementById("password_err");

                if ("true" == resp) {
                    //如果返回true,证明密码等于6，错误不提示
                    passwordErrEle.style.display = "none";
                } else {
                    //如果返回false,证明密码不等于6，错误提示
                    passwordErrEle.style.display = "";
                }

            }
        };
    }
</script>--%>

<!-- 1.引入js脚本-->
<script src="js/axios-0.18.0.js"></script>

<script>

    var passwordEle = document.getElementById("password");
    passwordEle.onblur = function () {
        var password = passwordEle.value
        /* 1：get请求方式*/
        axios({
            method: "get",
            url: "http://localhost:8080/web/axios_demo?password=" + password
        }).then(function (resp) {
            var data1 = resp.data;
            var passwordErrEle = document.getElementById("password_err");
            if (true == data1) {
                //如果返回true,证明密码等于6，错误不提示
                passwordErrEle.style.display = "none";
            } else {
                //如果返回false,证明密码不等于6，错误提示
                passwordErrEle.style.display = "";
            }
        });
    }

</script>
</body>
</html>

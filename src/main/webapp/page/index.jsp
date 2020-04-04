<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="page/css/login.css"/>
    <meta charset="utf-8">
    <title>Spring MVC 传参方式</title>
</head>
<body>
<div id="login_frame">
 
    <p id="image_logo"><img src="icon/dd.png" width="50" height="50" ></p>
 
<form action="user_login" role="post">
   <p><label class="label_input">ma-il</label><input type="text" id="username" name="mail" class="text_field"/></p>
        <p><label class="label_input">password</label><input type="text" id="password" name="password" class="text_field"/></p>
    <input id="btn_login" type="submit" value="提  交">

    
</form>

         <a href="registered">  <input type="button" id="btn_login" value="注册" /></a>
         
         
            <a id="forget_pwd" href="forget_pwd.html">忘记密码？</a>
     
</div>






</body>

</html>
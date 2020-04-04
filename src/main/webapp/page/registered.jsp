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
 
    <p id="image_logo"><img src="/data/555.jpg" width="50" height="50" ></p>
 
<form action="user_registered" method="post">
   <p><label class="label_input">mail</label><input type="text" id="username" name="mail" class="text_field"/></p>
        <p><label class="label_input">password</label><input type="password" type="hidden" id="password" name="password" class="text_field"/></p>
    <input id="btn_login" type="submit" value="注 册">

    
</form>

         <a href="jump2Index">  <input type="button" id="btn_login" value="返回" /></a>
         
         
     
</div>






</body>

</html>
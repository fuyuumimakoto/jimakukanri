<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
         <%@ page import="pojo.*" %>
<html>
<head>
    <title>bbs main page</title>
     <link rel="stylesheet" type="text/css" href="page/css/main.css"/>
    </head>
<body>
<%
int tid = ((Integer)(request.getAttribute("tid"))).intValue();
String uid= (String)request.getAttribute("uid");

%>

<div class="topnav">
<a href="Main?uid=<%=uid %>" >返回首页</a>

<a href="user_info?act_uid=<%=uid %>&uid=<%=uid %>">用户信息</a>

<a href="team?uid=<%=uid%>&tid=0&pagenum=1">群组</a>
</div>

<form action="taskCreate_post" role="post"  method="post">
    name：<input type="text" name="name"><br/>
    part：<input type="text" name="part"><br/>
       time：<input type="text" name="time"><br/>
    <input type="text" name="uid" value=<%=uid %> hidden="hidden"> 
<input type="text" name="tid" value=<%=tid %> hidden="hidden"> 
    <input type="submit" value="提  交">
   
    
</form>




</body>
</html>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="page/css/teamcreate.css"/>
    <title>Spring MVC 传参方式</title>
</head>
<body>
<%
String uid=(String)request.getAttribute("uid");
String isleader=(String)request.getAttribute("isleader");
%>
<div class="topnav">
 <form name="jump2Main" method="post" action="Main">
  <input type="hidden" name="uid" value="<%=uid %>" />
</form>
<a href="#" onclick="jump2Main.submit()">返回首页</a>

<form name="user_info" method="post" action="user_info">
  <input type="hidden" name="act_uid" value="<%=uid %>" />
    <input type="hidden" name="uid" value="<%=uid %>" />
</form>
<a href="#" onclick="user_info.submit()">用户信息</a>

<form name="team_info" method="post" action="team_info">
  <input type="hidden" name="uid" value="<%=uid %>" />
    <input type="hidden" name="tid" value="0" />
    <input type="hidden" name="pagenum" value="1" />
</form>
<a href="#" onclick="team_info.submit()">群组</a>
</div>


<form action="TeamCreate_post" role="post" method="post">
<input type="text" name="uid" value=<%=uid %> hidden="hidden"> 
   <input type="text" name="name">
   <input type="text" name="tid" value="0" hidden="hidden"> 
      <input type="text" name="pagenum" value="1" hidden="hidden"> 
    <input type="submit" value="提  交">

    
</form>

<%-- <p1 id="isleader" hidden="hidden"><%=isleader %></p1> --%>


<script>
 var oDiv =document.getElementById('isleader')
if(oDiv.innerHTML=="1")
	{
	 alert("用户只能管理一个群组！");
	 window.history.back();
	}


</script>

</body>
</html>
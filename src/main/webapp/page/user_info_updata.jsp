<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
                  <%@ page import="pojo.*" %>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="page/css/user_info.css"/>
    <title>user_info page</title>
</head>
<body>


<%String uid = request.getParameter("uid");  %> 

<div class="topnav">
<a href="Main?uid=<%=uid %>" >返回首页</a>

<a href="user_info?act_uid=<%=uid %>&uid=<%=uid %>">用户信息</a>

<a href="team?uid=<%=uid%>&tid=0&pagenum=1">群组</a>

</div>
<div class="main">
<p>mail:${user_info.getMail()}</p>
<form action="user_info_updata_requset"  method="post" enctype="multipart/form-data">

 选择文件:<input type="file" name="file" width="120px"> 
<input type="text"  readonly  unselectable="on" name="uid" value=<%=uid %>  hidden="hidden"> <br>
  用户名：<input type="text" name="name" value=${user_info.getName()}><br/>
  qq：<input type="text" name="qq_num" value=${user_info.getQq_num()}><br/>

      <input type="checkbox" name="checkbox" value="1">轴
    
    <input name="checkbox" type="checkbox" id="checkbox" value="2">翻
  
    <input name="checkbox" type="checkbox" id="checkbox" value="3">校
   


    <input type="submit" value="提  交">
    </div>
</form>
</body>
</html>
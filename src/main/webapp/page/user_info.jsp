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
<%
String uid= (String)request.getAttribute("uid");
String act_uid= (String)request.getAttribute("act_uid");
User_info user_info=(User_info) request.getAttribute("user_info");
%>
<div class="topnav">

<a href="Main?uid=<%=uid %>" >返回首页</a>

<a href="user_info?act_uid=<%=uid %>&uid=<%=uid %>">用户信息</a>

<a href="team?uid=<%=uid%>&tid=0&pagenum=1">群组</a>



<%
if(uid.equals(act_uid))
{
	
	out.write("<a href='User_infoUpdata?uid="+uid+"' >updata</a> ");
}


%>
</div>

<div class="userinfo">
<img src="/data/user/<%=user_info.getIcon() %>"  width="50" height="50" >
<p>username:<%=user_info.getName() %></p>
<%
String chartString="";
if(user_info.getRole()==1)
	{
	chartString="轴";
	}
if(user_info.getRole()==2)
{
	chartString="翻";
}
if(user_info.getRole()==3)
{
	chartString="校";
}
if(user_info.getRole()==4)
{
chartString="轴 翻";
}
if(user_info.getRole()==5)
{
chartString="轴 校";
}
if(user_info.getRole()==6)
{
chartString="翻 校";
}
if(user_info.getRole()==7)
{
chartString="一条龙";
}
	%>
<p>角色：<%=chartString %></p>
<p>QQ:<%=user_info.getQq_num() %></p>

<%
if(!uid.equals(act_uid))
	out.write("<a href='jump2chat?uid="+uid+"&act_uid="+act_uid+"'>chat</a>");
%>
    
   </div> 
    
 
</body>
</html>
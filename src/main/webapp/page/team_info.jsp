<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
         <%@ page import="pojo.*" %>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="page/css/main.css"/>
    <title>bbs main page</title>
    <style>

</style>
</head>
<body>


<%
ArrayList<User_info> user_info_member = (ArrayList) request.getAttribute("user_info_member");
ArrayList<User_info> user_info_app = (ArrayList) request.getAttribute("user_info_app");
String uid=(String)request.getAttribute("uid");
Team team=(Team)request.getAttribute("team");
String isleader=(String)request.getAttribute("isleader");
%>
<div class="topnav">
<a href="Main?uid=<%=uid %>" >返回首页</a>

<a href="user_info?act_uid=<%=uid %>&uid=<%=uid %>">用户信息</a>

<a href="team?uid=<%=uid%>&tid=0&pagenum=1">群组</a>
</div>


<div class="member_list">
<%
for(int i=0;i<user_info_member.size();i++)
{
	out.write("<div class='member'>");
	out.write(
	"<img src='/data/user/"+user_info_member.get(i).getId()+"/"+user_info_member.get(i).getIcon()+"'  width='50' height='50' >"+
			"<a href='user_info?act_uid="+uid+"&uid="+user_info_member.get(i).getId()+"'>"+user_info_member.get(i).getName()+"</a>"+
			"</div>");
	
	
}
out.write("</div>") ;


if(user_info_app!=null&&isleader.equals("1"))
{
	out.write("<div class='app_list'>");
	for(int i=0;i<user_info_app.size();i++)
	{
		out.write("<div class='app'>");
		out.write(
		"<img src='/data/user/"+user_info_app.get(i).getId()+"/"+user_info_app.get(i).getIcon()+"'  width='50' height='50' >"+
				"<a href='user_info?act_uid="+uid+"&uid="+user_info_app.get(i).getId()+"'>"+user_info_app.get(i).getName()+"</a>"+
						"<a href='TeamAddUser?&uid="+uid+"&tid="+team.getId()+"&app_uid="+user_info_app.get(i).getId()+"'>join</a>"+
				"</div>");
	}
	out.write("</div>");
	
	
	
	out.write("<div class='1'> ");
	out.write("<form action='team_info_updata'  method='post' enctype='multipart/form-data'>");
	out.write(" <input type='text' name='name' value="+team.getName() +"> ");
	out.write("<input type='text' name='uid' value='"+uid+"' hidden='hidden'>");
	out.write("<input type='text' name='tid' value='"+team.getId()+"' hidden='hidden'>");
	out.write(" 选择文件:<input type='file' name='file'> ");
	out.write("<input type='submit' value='提  交'>");
	out.write("</form></div>");
}

%>



</body>
</html>
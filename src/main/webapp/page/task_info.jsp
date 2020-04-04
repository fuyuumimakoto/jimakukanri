<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
                <%@ page import="pojo.*" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Spring MVC 传参方式</title>
     <link rel="stylesheet" type="text/css" href="page/css/main.css"/>
</head>
<%
task_info task_info = (task_info) request.getAttribute("task_info");
String[]name = (String[]) request.getAttribute("name");
String[]md5ids = (String[]) request.getAttribute("md5ids");
String uid= (String)request.getAttribute("uid");
List<member> members=task_info.getMembers();
boolean isInMember=false;
int markMemberid=0;
%>
<body>


 <div class="topnav">
<a href="Main?uid=<%=uid %>" >返回首页</a>

<a href="user_info?act_uid=<%=uid %>&uid=<%=uid %>">用户信息</a>

<a href="team?uid=<%=uid%>&tid=0&pagenum=1">群组</a>
</div>

 
<table border="1">
  <caption><%=task_info.getName() %></caption>
  <tr>
    <th>担当</th>
   <%
   for(int i=0;i<task_info.getPart()-1;i++)
   {
	   out.write("<th>"+(i*task_info.getTime()/task_info.getPart())+"--"+((i+1)*task_info.getTime()/task_info.getPart())+"</th>");
   }
   out.write("<th>"+(task_info.getTime()- task_info.getTime()/task_info.getPart())+"--end</th>");
   %>
  </tr>
  <tr>
    <th>轴</th>
    <% 
 for(int i=0;i<task_info.getPart();i++)
 {
	 if(task_info.getFiles_name()[i].equals(""))
	 {
		 out.write("<td>"); 
	 }
	 else{
		 out.write("<td style='background-color:#FFFF00'>");
		 out.write("<a href='task_file_down?tid="+task_info.getTid()+"&taskid="+task_info.getId()+"&uid="+uid+"&file="+task_info.getFiles_name()[i]+"'><button>下载</button></a>");
	 }
	out.write(name[i]);
	if(uid.equals(md5ids[i]))
	{
		out.write("<form action='task_file_up'  method='post' enctype='multipart/form-data'>");
		out.write("<input type='file' name='file' width='60px'> ");
		out.write("<input type='text' name='uid'  value='"+uid+"' hidden='hidden'>");
		out.write("<input type='text' name='tid'  value='"+task_info.getTid()+"' hidden='hidden'>");
		out.write("<input type='text' name='taskid'  value='"+task_info.getId()+"' hidden='hidden'>");
		out.write("<input type='text' name='mark'  value='"+i+"' hidden='hidden'>");
		out.write("<input type='submit' value='提  交'> ");
		out.write("</form>");
	}
	 out.write("</td>");
 }
    %>>
  </tr>
  
  <tr>
    <th>翻</th>
      <% 
 for(int i=0;i<task_info.getPart();i++)
 {
	 if(task_info.getFiles_name()[i+task_info.getPart()].equals(""))
	 {
		 out.write("<td>"); 
	 }
	 else{
		 out.write("<td style='background-color:#FFFF00'>");
	 }
		out.write(name[i+task_info.getPart()]);
		if(uid.equals(md5ids[i+task_info.getPart()]))
		{
			out.write("<button>上传文件</button>");
		}
	 out.write("</td>");
 }
    %>>
  </tr>
  
  <tr>
    <th>校</th>
      <% 
 for(int i=0;i<task_info.getPart();i++)
 {
	 if(task_info.getFiles_name()[i+task_info.getPart()*2].equals(""))
	 {
		 out.write("<td>"); 
	 }
	 else{
		 out.write("<td style='background-color:#FFFF00'>");
		 out.write("<a href='task_file_down?tid="+task_info.getTid()+"&taskid="+task_info.getId()+"&uid="+uid+"&file="+task_info.getFiles_name()[i+task_info.getPart()*2]+"'><button>下载</button></a>");
	 }
		out.write(name[i+task_info.getPart()*2]);
		if(uid.equals(md5ids[i+task_info.getPart()*2]))
		{
			out.write("<form action='task_file_up'  method='post' enctype='multipart/form-data'>");
			out.write("<input type='file' name='file' width='60px'> ");
			out.write("<input type='text' name='uid'  value='"+uid+"' hidden='hidden'>");
			out.write("<input type='text' name='tid'  value='"+task_info.getTid()+"' hidden='hidden'>");
			out.write("<input type='text' name='taskid'  value='"+task_info.getId()+"' hidden='hidden'>");
			out.write("<input type='text' name='mark'  value='"+(i+task_info.getPart()*2)+"' hidden='hidden'>");
			out.write("<input type='submit' value='提  交'> ");
			out.write("</form>");
		}
	 out.write("</td>");
 }
    %>>
  </tr>
  
</table> 

<a href="jump2task_info_modify?uid=<%=uid %>&taskid=<%=task_info.getId() %>&tid=<%=task_info.getTid() %>"><button>修改</button></a>


</body>
</html>
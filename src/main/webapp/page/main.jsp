<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
         <%@ page import="pojo.*" %>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="page/css/main.css"/>
    <title>bbs main page</title>
    <style>
#para1
{
	text-align:center;
	color:red;
} 
</style>

</head>
<body>




<%
ArrayList<Block> blist = (ArrayList) request.getAttribute("Blist");
String uid=(String)request.getAttribute("uid");

%>
<div class="topnav">
<a href="Main?uid=<%=uid %>" >返回首页</a>

<a href="user_info?act_uid=<%=uid %>&uid=<%=uid %>">用户信息</a>

<a href="team?uid=<%=uid%>&tid=0&pagenum=1">群组</a>
</div>
 <div class="main">+
  

<%
//构建种类（categories）列表
List<String> catelist=new ArrayList();
 
for(int i = 0; i < blist.size(); i++) {
	boolean issame=false;
	for(int j=0;j<catelist.size();j++)
	{
		if(blist.get(i).getCategory().equals(catelist.get(j)))
			issame=true;
}
	if(!issame)
	{
		catelist.add(blist.get(i).getCategory());
	}
	
}
for(int i=0;i<catelist.size();i++)
{

	out.write("<div class='category'>");
	out.write("<div class='categoryimg'>");
	out.write("<img src='");
	out.write(catelist.get(i));
	out.write("' width='50' height='50'>");
	out.write("</div>");
		
	
	
	
	for(int j=0;j<blist.size();j++)
	{
		if(blist.get(j).getCategory().equals(catelist.get(i)))
		{
			
			 String bid=""+blist.get(j).getId();
			out.write("<div class='block'>");
			out.write("<form name='"+blist.get(j).getName()+"' method='post' action='block_info'>");
			out.write("  <input type='hidden' name='uid' value='"+uid+"' />");
			out.write("  <input type='hidden' name='bid' value='"+bid+"' />");
			out.write("</form>");
			out.write("<a href='#");
			out.write(blist.get(j).getName());
			out.write("'   onclick='"+blist.get(j).getName()+".submit()'>");
			out.write("<img src='" + blist.get(j).getIcon()+"'width='50' height='50'>"+blist.get(j).getName());
	
			out.write("</a>");
		
		
			out.write("</div>");
		}
	}
	out.write("</div>");
	
}
%>



 </div>


      

</body>
</html>
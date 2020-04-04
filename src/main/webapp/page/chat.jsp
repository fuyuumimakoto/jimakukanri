<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
         <%@ page import="pojo.*" %>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="page/css/chat.css"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
int act_uid = ((Integer)(request.getAttribute("act_uid"))).intValue();
int uid = ((Integer)(request.getAttribute("uid"))).intValue();
ArrayList<User_info> ulist = (ArrayList) request.getAttribute("user_infos");//last is the actuser
ArrayList<Chat_log> clist = (ArrayList) request.getAttribute("chat_logs");
int mark=0;
%>


<div class="topnav">
 <a href="jump2Main?uid=<%=uid%>">返回首页</a>
 <a href="user_info?act_uid=<%=uid%>&uid=<%=uid%>">user_info</a>
<a href="team_info?uid=<%=uid%>&tid=0">team_info</a>
</div>

<%
out.write("<div clss=''>");
for(int i=0;i<ulist.size()-1;i++)
{
	out.write("<div class=''>");
	out.write("<img>");
	out.write("<a>"+ulist.get(i).getName()+"</a>");
	out.write("</div>");
	
	if(ulist.get(i).getId()==uid)
	{
		mark=i;
	}
}
out.write("</div>");


if(clist!=null)
{
	out.write("<div class='msgbox'>"+
" <ul class='contentbox'>");
	for(int i=0;i<clist.size();i++)
{
	if(act_uid==clist.get(i).getUid())
	{  
		out.write("<div class='msgleft'>");
		out.write("<p>"+clist.get(i).getDate()+"</p>");
		out.write("<img class='imgleft' src='/data/user/"+ulist.get(ulist.size()-1).getId()+"/"+ulist.get(ulist.size()-1).getIcon()+"'width='50' height='50' >");
		out.write("<li class='msgContent left'>"+clist.get(i).getText()+"</li>");
		out.write("</div>");
		out.write("    <div style='clear:both'></div>");
	}
	
	if(uid==clist.get(i).getUid())
	{
		out.write("<div class='msgright'>");
		out.write("<p>"+clist.get(i).getDate()+"</p>");
		out.write("<img class='imgright' src='/data/user/"+ulist.get(mark).getId()+"/"+ulist.get(mark).getIcon()+"'  width='50' height='50' >");
		out.write("<li class='msgContent right'>"+clist.get(i).getText()+"</li>");
		out.write("</div>");
		out.write("    <div style='clear:both'></div>");
	}
}
	out.write("</ul> ");
}
%>

<div class="input">
<form action="sendchatmsg" role="form">
<input type="text" name="uid" value=<%=uid %> hidden="hidden"> 
<input type="text" name="act_uid" value=<%=act_uid %> hidden="hidden" > 
  <textarea class="input_text" type="text" name="text"></textarea>
    <input class='btn_sumbit ' type="submit" value="提  交">
</form>
</div>

</div>

</body>
</html>
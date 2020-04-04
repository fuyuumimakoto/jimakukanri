<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
         <%@ page import="pojo.*" %>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="page/css/block.css"/>
    <title>bbs main page</title>
</head>
<body>


<%
ArrayList<Forusm> flist = (ArrayList) request.getAttribute("flist");
String uid=(String)(request.getAttribute("uid"));

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

<form name="team" method="post" action="team">
  <input type="hidden" name="uid" value="<%=uid %>" />
    <input type="hidden" name="tid" value="0" />
    <input type="hidden" name="pagenum" value="1" />
</form>
<a href="#" onclick="team.submit()">群组</a>
</div>



<div class="forusm_area">
<table>
<tr>
<th>title</th>
<th>user</th>
<th>createdate</th>
<th>lastredate</th>
</tr>
<%

	

for(int j=0;j<flist.size();j++)
{
	
		 String fid=""+flist.get(j).getId();
		 String bid=""+flist.get(j).getBid();
		 String blockclass;
		 if(j%2==0){
			blockclass="forusm_1";
		 }
		 else{
			blockclass="forusm_2";
		 }
		out.write("<tr class='"+blockclass +"'>");
		out.write("<td><a class='title' href='forusm?fid=");
		out.write(fid);
		out.write("&uid=");
		out.write(uid); 
		out.write("&bid=");
		out.write(bid); 
		out.write("&pagenum=1");
		out.write("'>");
		out.write(flist.get(j).getTitle() );
		out.write("</a></td>");
		
		out.write("<td><a class='forusm_creater'>");
		out.write(""+flist.get(j).getUid());
		out.write("</a></td>");
		
		out.write("<td class='date'>");
		out.write(flist.get(j).getCreate_time());
		out.write("</td>");
		
		out.write("<td class='date'>");
		out.write(flist.get(j).getRe_time());
		out.write("</td>");
		
		out.write("</tr>");
}
%>
</table>
</div>

<div class="input">
<form action="Postforusm" role="form">
<input type="text" name="uid" value=<%=uid %> hidden="hidden"> 
<input type="text" name="bid" value=<%=flist.get(0).getBid() %> hidden="hidden" > 
  <input class="input_title" type="text" name="title" >
  <textarea class="input_text" type="text" name="text"></textarea>
    <input class='btn_sumbit ' type="submit" value="提  交">
    
</form>
</div>




</body>
</html>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
         <%@ page import="pojo.*" %>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="page/css/forusm.css"/>
    <title>bbs main page</title>
</head>
<body>


<%
ArrayList<Forusm_info> FInofList = (ArrayList) request.getAttribute("FInfoList");
String act_uid=(String)request.getAttribute("uid");
Block block=(Block)request.getAttribute("block");
int pagenum= ((Integer)(request.getAttribute("pagenum"))).intValue();

int totalpage;
totalpage=FInofList.size()/5+1;
%>
<div class="topnav">
<form name="jump2Main" method="post" action="Main">
  <input type="hidden" name="uid" value="<%=act_uid %>" />
</form>
<a href="#" onclick="jump2Main.submit()">返回首页</a>

<form name="user_info" method="post" action="user_info">
  <input type="hidden" name="act_uid" value="<%=act_uid %>" />
    <input type="hidden" name="uid" value="<%=act_uid %>" />
</form>
<a href="#" onclick="user_info.submit()">用户信息</a>

<form name="team" method="post" action="team">
  <input type="hidden" name="uid" value="<%=act_uid %>" />
    <input type="hidden" name="tid" value="0" />
    <input type="hidden" name="pagenum" value="1" />
</form>
<a href="#" onclick="team.submit()">群组</a>

<form name="block_info" method="post" action="block_info">
  <input type="hidden" name="uid" value="<%=act_uid %>" />
    <input type="hidden" name="bid" value="<%=block.getId() %>" />

</form>
<a href="#" onclick="block_info.submit()"><%=block.getName() %></a>


</div>



<div class="jumper">
<form action="forusm" role="post">
<input type="text" name="uid" value=<%=act_uid %> hidden="hidden"> 
<input type="text" name="bid" value=<%=block.getId() %> hidden="hidden"> 
<input type="text" name="fid" value=<%=FInofList.get(0).getFid() %> hidden="hidden" > 
<input type="text" name="pagenum"  > 
<p><%=totalpage %></p>
    <input type="submit" value="re">
</form>
</div>


<table>
<tr>
  <th>回复</th>
  <th><%=block.getName() %></th>
</tr>
<%
if(pagenum*5-4>FInofList.size())
{
	pagenum=1;
}
int endnum;
if(pagenum*5>FInofList.size())
{
	endnum=FInofList.size();
}
else{
	endnum=pagenum*5;
}
for(int j=pagenum*5-5;j<endnum;j++)
{
	
		 String uid=""+FInofList.get(j).getUid();
		 String text=""+FInofList.get(j).getText();
		 int num=j+1;
		 out.write("<tr class='line_1'>");
		 out.write("<td class='row_1'>"+   "<a href='user_info?uid="+uid+"&act_uid="+act_uid+"'>"+FInofList.get(j).getUid()+"</a>"+ "</td>");
		 out.write("<td>发表于:"+FInofList.get(j).getRe_time()   +"     #"+num+"</td>");
		 out.write("</tr>");
		 out.write("<tr class='line_2'>");
		 out.write("<td class='row_1'>"+"<img src='icon/dd.png' width='50' height='50' >"+"</td>");
		 out.write("<td>"+FInofList.get(j).getText()+"</td>");
		 out.write("</tr>");
	

}


%>

</table>
<!-- 信息框体 -->





<div class="re">
<form action="reforusm" role="post">


<input type="text" name="uid" value=<%=act_uid %> hidden="hidden"> 
<input type="text" name="bid" value=<%=block.getId() %> hidden="hidden"> 
<input type="text" name="fid" value=<%=FInofList.get(0).getFid() %> hidden="hidden" > 
<input type="text" name="pagenum" value=<%=FInofList.size()/5+1 %> hidden="hidden" > 
   
   <textarea type="text" name="text"></textarea>
    <input type="submit" value="re">
    
</form>
</div>
<!-- 回复 -->







</body>
</html>
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
String[][] name = (String[][]) request.getAttribute("name");
int[][] id = (int[][]) request.getAttribute("id");
String uid= (String)request.getAttribute("uid");
String[] name_old = (String[]) request.getAttribute("name_old");
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

<script language="javascript">
 
function change(){
	
	 var part=document.getElementById("part_num");
  
	for(var i=0;i<part.innerHTML;i++)
	{
		var select_id=document.getElementById("select"+i).value;
		 
         document.getElementById("input"+i).value = select_id;
  
	}
	
     
 
}
</script>
 
<p id="part_num"><%=task_info.getPart()*3 %></p>

 
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
	 out.write("<td>");
	 out.write("<select id='select"+i+"' onchange='change()'>");
	 if(name_old[i].equals(""))
	 {
		 out.write("<option value='0' selected='selected'> "+" "+"</option>");
	 }
	 else{
	 out.write("<option value='0'> "+" "+"</option>");
	 }
	 for(int j=0;j<name[0].length;j++)
	 {

		 if(name_old[i].equals(name[0][j]))
		 {
			 out.write("<option value='"+id[0][j] +"' selected='selected'> "+name[0][j]+"</option>");
		 }
		 else{
		 out.write("<option value='"+id[0][j] +"'> "+name[0][j]+"</option>");
		 }
	 }
	 out.write("</select>");
	 out.write("</td>");
 }
    %>>
  </tr>
  
  <tr>
    <th>翻</th>
      <% 
 for(int i=0;i<task_info.getPart();i++)
 {
	 out.write("<td>");
	 out.write("<select id='select"+(i+task_info.getPart())+"'  onchange='change()'>");
	 if(name_old[i+task_info.getPart()].equals(""))
	 {
		 out.write("<option value='0' selected='selected'> "+" "+"</option>");
	 }
	 else{
	 out.write("<option value='0'> "+" "+"</option>");
	 }
	 for(int j=0;j<name[1].length;j++)
	 {
		 if(name_old[i+task_info.getPart()].equals(name[1][j]))
		 {
			 out.write("<option value='"+id[1][j] +"' selected='selected'> "+name[1][j]+"</option>");
		 }
		 else{
		 out.write("<option value='"+id[1][j] +"'> "+name[1][j]+"</option>");
		 }
	 }
	 out.write("</select>");
	 out.write("</td>");
 }
    %>>
  </tr>
  
  <tr>
    <th>校</th>
      <% 
 for(int i=0;i<task_info.getPart();i++)
 {
	 out.write("<td>");
	 out.write("<select id='select"+(i+task_info.getPart()*2)+"' onchange='change()'>");
	 if(name_old[i+task_info.getPart()*2].equals(""))
	 {
		 out.write("<option value='0' selected='selected'> "+" "+"</option>");
	 }
	 else{
	 out.write("<option value='0'> "+" "+"</option>");
	 }
	 for(int j=0;j<name[2].length;j++)
	 {
		 if(name_old[i+task_info.getPart()*2].equals(name[2][j]))
		 {
			 out.write("<option value='"+id[2][j] +"' selected='selected'> "+name[2][j]+"</option>");
		 }
		 else{
		 out.write("<option value='"+id[2][j] +"'> "+name[2][j]+"</option>");
		 }
	 }
	 out.write("</select>");
	 out.write("</td>");
 }
    %>>
  </tr>
  
</table> 

<form action="task_info_updata">
<%
for(int i=0;i<task_info.getPart()*3;i++)
{
	out.write("<input type='text' hidden='hidden' name='sel_id' id='input"+i+"'>");
}
%>
<input type="text"   name="uid" value=<%=uid %>  hidden="hidden"> 
<input type="text"   name="tid" value=<%=task_info.getTid() %>  hidden="hidden"> 
<input type="text"   name="taskid" value=<%=task_info.getId() %>  hidden="hidden"> 
<input type="submit" value="提  交"  >
</form>

</body>
</html>
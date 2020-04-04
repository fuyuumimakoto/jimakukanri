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
ArrayList<Team> tlist = (ArrayList) request.getAttribute("tlist");
String uid=(String)request.getAttribute("uid");
int [] isapp = (int[]) request.getAttribute("isapp");
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

<div class="re">
<form action="TeamSearch" role="post" method="post">


<input type="text" name="name"  > 
<input type="text" name="uid" value=<%=uid %> hidden="hidden"> 
    <input type="submit" value="re">
    
</form>
</div>
     <div> 
      
      <%
      if(tlist!=null)
      {
    	  out.write("<table><tr>"+
    		     " <th>name</th>"+
    		      "<th>user</th>"+
    		      "</tr>");
    	  for(int i=0;i<tlist.size();i++)
    	  {
    		  out.write("<tr><td>");
    		  out.write("<img src='/data/team/"+tlist.get(i).getId()+"/"+tlist.get(i).getIcon()+"' width='50' height='50' >");

    		  out.write("<p>"+tlist.get(i).getName()+"</p>");
    		  out.write("</td><td>");
    		  if(isapp[i]==0)
    		  {
    			  out.write("<form name='"+tlist.get(i).getId()+"' method='post' action='TeamApplication'>");
    				out.write("  <input type='hidden' name='uid' value='"+uid+"' />");
    				out.write("  <input type='hidden' name='tid' value='"+tlist.get(i).getId()+"' />");
    				out.write("  <input  type='submit' name='post'>");
    				out.write("</form>");
    				
    				
    	
    		  }
    			
    		  else{
    			  out.write("<p>isjoin</p></td></tr>");
    		  }
    	  }
      }
   


%>
      </table>
      </div>

</body>
</html>
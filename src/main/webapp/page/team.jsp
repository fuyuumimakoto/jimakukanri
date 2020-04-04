<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
         <%@ page import="pojo.*" %>
<html>
<head>
    <title>bbs main page</title>
 <link rel="stylesheet" type="text/css" href="page/css/team.css"/>
</head>
<body>
<%

ArrayList<Team> tlist = (ArrayList) request.getAttribute("tList");
ArrayList<task> tasklist = (ArrayList) request.getAttribute("tasklist");

String uid= (String)request.getAttribute("uid");

int tid = ((Integer)(request.getAttribute("tid"))).intValue();
int pagenum = ((Integer)(request.getAttribute("pagenum"))).intValue();  
int totalpage=0;
if(tasklist!=null)
{totalpage=tasklist.size()/20;
if(tasklist.size()%20!=0)
	totalpage++;}
%>

<div class="topnav">
<a href="Main?uid=<%=uid %>" >返回首页</a>

<a href="user_info?act_uid=<%=uid %>&uid=<%=uid %>">用户信息</a>

<a href="team?uid=<%=uid%>&tid=0&pagenum=1">群组</a>


<a href="teamCreate?uid=<%=uid %>" >创建群组</a>


<a href="TeamSearch?uid=<%=uid %>&name=" >查找群组</a>





<%
if(tid!=0)
{

	

	out.write("<a href='taskCreate?uid="+uid+"&tid="+tid);
	out.write("'   >项目创建 </a>");
	

	out.write("<a href='Team_info?uid="+uid+"&tid="+tid);
	out.write("'>群组信息</a>");
}


%>
</div>


<%


if(tlist==null)
{
	out.write("<p>未加入</p>");
}
else
{

	
	
	out.write("<div class='teamlist' >");
	
	

	for(int i=0;i<tlist.size();i++)
	{out.write("<form name='"+tlist.get(i).getName()+i+"' method='post' action='team'>");
	out.write("  <input type='hidden' name='uid' value='"+uid+"' />");
	out.write("  <input type='hidden' name='tid' value='"+tlist.get(i).getId()+"' />");
	out.write("  <input type='hidden' name='pagenum' value='1' />");
	out.write("</form>");
	out.write("<a href='#");
	out.write(tlist.get(i).getName());
	out.write("'   onclick='"+tlist.get(i).getName()+i+".submit()'>");
	out.write("<div class='team'>"+
	"<img src='/data/team/"+tlist.get(i).getId()+"/"+tlist.get(i).getIcon()+"'  width='50' height='50' >"+
			"<p>"+tlist.get(i).getName()+"</p>"+
			"</div>"+
	"</a>\n");
	}
	
	  out.write("</div>") ;
	  //for team list
 
	  
	  out.write("<div class='tasklist'>");//task list

if(totalpage>=2)
{out.write("<div class='jumper'>");
out.write("<form action='team_info' role='post'>");
out.write("<input type='text' name='uid' value='"+uid+"' hidden='hidden'> ");
out.write("<input type='text' name='tid' value='"+tid+"' hidden='hidden'> ");
out.write("<input type='text' name='pagenum' > ");
out.write("/"+totalpage+"/"+tasklist.size());
out.write("   <input type='submit' value='re'>");
out.write("</form></div>");
}	  


	  if(tasklist!=null)
	  {
		  out.write("<table>"+
	
	  "<tr>"+
			  " <th>name</th>"+
	  "<th>time</th>"+		
			  "</tr>"
			  );


	  if(pagenum*20-19>tasklist.size())
	  {
	  	pagenum=1;
	  }
	  int endnum;
	  if(pagenum*20>tasklist.size())
	  {
	  	endnum=tasklist.size();
	  }
	  else{
	  	endnum=pagenum*20;
	  }
	  String type;
	  if(true)
	  {
		  type="line_1";
	  }
	  else{
		  type="line_2";
	  }
	  for(int j=pagenum*20-20;j<endnum;j++)
	  {
	  	
	
	  		 int num=j+1;
	  		 
	  		out.write("<form name='"+"jump2task_info"+j+"' method='post' action='taskinfo'>");
	  		out.write("  <input type='hidden' name='uid' value='"+uid+"' />");
	  		out.write("  <input type='hidden' name='taskid' value='"+tasklist.get(j).getId()+"' />");
	  		out.write("  <input type='hidden' name='tid' value='"+tid+"' />");
	  		out.write("</form>");
	  	
	  		 
	  		 out.write("<tr class='"+type+"'>");
	  		 out.write("<td>");
	  		 
	  				 
		  		out.write("<a href='taskinfo?uid="+uid+"&taskid="+tasklist.get(j).getId()+"&tid="+tid+"'>"+
		  		tasklist.get(j).getName()+
		  				"</a>"+ "</td>");
	  		out.write("<td>发表于:"+tasklist.get(j).getCreatedate()+"</td>");
	  		 out.write("</tr>\n");
	  		
	  	

	  }

out.write("	  </table>");
	  }
}

%>
</div> <!-- end of task list -->

<!-- <script>
if(true)
	{
	window.alert(5 + 6);
	}
</script> -->




      

</body>
</html>
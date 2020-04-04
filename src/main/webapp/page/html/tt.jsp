<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>B page</title>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
</head>
<%
String url=(String)(request.getAttribute("url"));

for(int i=0;i<url.length();i++)
{
	if(String.valueOf(url.charAt(i)).equals("c"))
	{
		if(String.valueOf(url.charAt(i+1)).equals("o"))
			if(String.valueOf(url.charAt(i+2)).equals("m"))
			{
				 String lasturl = url.substring(i+4);
				 lasturl=lasturl+"?controls=0";
		String headurl="https://www.youtube.com/";
		String  centerurl="" ;
		for(int j=0;j<lasturl.length();j++)
		{
				if(String.valueOf(url.charAt(j)).equals("v"))
				{
					 centerurl = lasturl.substring(j+3);
				}
		}
		
		
		url=headurl+centerurl;
			}
	}
}
%>
<body>
<iframe width="560" height="315" src="https://www.youtube.com/embed/5cac-HshyCA?controls=0" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

<%=url %>

https://www.youtube.com/watch?v=5cac-HshyCA
</body>
</html>
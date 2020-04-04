<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传下载</title>
</head>
<script type="text/javascript">
    //批量处理，inventoryListSubmit button id
   $("input[name='request']").click(function(){
	window.close();
        var members= new Array();
        members.push({uid: "1", role: "1"});
        members.push({uid: "2", role: "2"});
        $.ajax({
               url: $.wap.url+"/updateFormList",
            type: 'POST',
            data: $.toJSON(members),
            dataType:'json',
            contentType:'application/json;charset=utf-8',
            success: function (data) {
                if(data.status){
                    $.Pro(data.info);
                    setTimeout("goBack()",1000); 
                }else{
                    $.Pro('网络错误');
                }
                disabledBtn("#cancel");
            },
            error: function (data) {
                console.log(data.status);
            }
        });
    });

</script>


<body>
    <input type="button" name="request" value="请求后台" style="width:200px;height:50px;background-color:red;margin-bottom:20px;">

    <form action="${pageContext.request.contextPath }/file/up_usericon"
        method="post" >
 
        <input type="text" name="uid"  value="1" width="120px"> 
        <input
            type="submit" value="传">
    </form>
    <hr>
    <form action="${pageContext.request.contextPath }/file/down"
        method="get">
        <input type="submit" value="下载">
    </form>
</body>
</html>
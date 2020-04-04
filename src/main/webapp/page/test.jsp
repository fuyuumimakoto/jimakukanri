<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false" %>
         <%@ page import="pojo.*" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
 
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script type="text/javascript" src="login.js"></script>
</head>
 
 <style type="text/css">
*{font-size: 14px; padding:0; margin:0;}
.msgbox{
    position: relative;
    margin: 20px auto;
    border: 1px solid steelblue;
    width: 430px;
    height: 400px;
}
.msgInput{
    display: block;
    width: 406px;
    height: 60px;
    margin: 10px auto;

}
.sendbtn{
    position: absolute;
    width: 100px;
    height: 29px;
    bottom: 5px;
    right: 10px;
}
.contentbox{
    list-style: none;
    width: 410px;
    height: 280px;
    margin: 5px auto;
    border: 1px dotted #D1D3D6;
    overflow-y: scroll;
}
.msgContent{
    width:auto;
    max-width: 250px;
    height: auto;
    word-break: break-all;
    margin: 5px;
    padding: 3px;
    border-radius: 5px;
}

.content .left{
    float: left;
    text-align: left;
    background-color: lightgrey;
}
.content .right{
    float: right;
    text-align: right;
    background-color: yellowgreen;
}

.msg
{
  text-align: left;
   float: left;
}

.imgleft
{   float: left;
 text-align: left;
}

</style>

 
<body>
    <div class="msgbox">
        <ul class="contentbox">
        <div class="msg">
          <p> 2020/3/14</p>
        <img class="imgleft" src="/data/555.jpg" width="50" height="50" >
            <li class="msgContent left">hello?</li>
            </div>
            <div style="clear:both"></div>
            <li class="msgContent left">hello</li>
            <div style="clear:both"></div>
            <li class="msgContent right">hi</li>
            <div style="clear:both"></div>
            <li class="msgContent left">hehe</li>
            <div style="clear:both"></div>
            <li class="msgContent left">goodbye</li>
            <div style="clear:both"></div>
            <li class="msgContent right">。。。。</li>
            <div style="clear:both"></div>
            <li class="msgContent right">sdfasdsadfassdfsdfsdfdsfsdfsdfsdfdfasdffasdfasfdasd fasd fasd fasdfasdfasdf</li>
            <div style="clear:both"></div>
            <li class="msgContent right"> 哈哈</li>
            <div style="clear:both"></div>
        </ul>
        <textarea class="msgInput"></textarea>
        <button  class="sendbtn">发送</button>
    </div>
</body>
</html>

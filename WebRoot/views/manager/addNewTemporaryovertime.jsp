<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addNewTemporaryOvertime.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>经理新增临时加班活动</h1>
    
    <form action="manager/addNewTemporaryOvertime" method="post">
    	请输入开始时间：<input type="text" name="t_overtime_start"/><br/>
    	请输入结束时间：<input type="text" name="t_overtime_end"/><br/>
    	请输入发起原因：<input type="text" name="t_o_reason"/><br/>
    	<input type="submit" value="提交"/>
    </form>
  </body>
</html>
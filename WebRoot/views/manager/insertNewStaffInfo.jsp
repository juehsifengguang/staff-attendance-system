<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'insertNewStaffInfo.jsp' starting page</title>
    
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
    <h1>经理新增员工信息</h1>
    
    <form action="manager/insertNewStaffInfo" method="post">
                  请输入员工id：     <input type="text"  name="s_id"/><br/>
               请输入员工姓名：  <input  type="text"  name="s_name"/><br/>
     	请输入入职时间： <input type="text" name="entry_time"/><br/>
    	   请输入部门id：    <input type="text" name="dep_id"/><br/>
    	   身份类别是：<input type="text" name="identity"/><br/>
    	   初始密码：       <input type="text" name="s_pass"/><br/>
    	<input type="submit" value="提交"/>
    </form>
  </body>
</html>
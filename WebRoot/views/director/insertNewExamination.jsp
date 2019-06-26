<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'insertNewExamination.jsp' starting page</title>
    
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
    <h1>主管新增审批意见表</h1>
    
    <form action="director/insertNewExamination" method="post">
                  请输入审批意见表id：     <input type="text"  name="e_id"/><br/>
                  请输入审批意见：     <input type="text"  name="e_comment"/><br/>
                  请假/加班？：<input type="text"   name="is_overtime"/><br/>
                  记录id是：  <input type="text"  name="record_id"/><br/>
    	<input type="submit" value="提交"/>
    </form>
  </body>
</html>
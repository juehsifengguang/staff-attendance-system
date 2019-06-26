<%@page import="com.as.entity.Staff"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'selectOvertimeRecord.jsp' starting page</title>
    
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
  <%
  	request.setCharacterEncoding("UTF-8");
  	
  	//获得参数
  	Staff staff = (Staff)request.getAttribute("staff");
  	Integer s_id = (Integer)request.getAttribute("s_id");
  	
  	
   %>
   <form action="staff/staffSelectStaffInfo" method="post">
      请输入员工id   <input type="text"  name="s_id"/><br/>
      	<input type="submit" value="提交"/>
   </form>

    <table>
    <tr>
    <td>姓名</td>
    <td>部门</td>
    <td>身份</td>
    <td>密码</td>
    </tr>
    <tr>
    <td><%=staff.getS_name() %></td>
    <td><%=staff.getDep_id() %></td>
    <td><%=staff.getIdentity() %></td>
    <td><%=staff.getS_pass() %></td>
    </table>
  </body>
</html>
    
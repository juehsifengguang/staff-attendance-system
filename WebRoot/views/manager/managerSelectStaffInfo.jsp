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
    
    <title>My JSP 'directorSelectAllExamination.jsp' starting page</title>
    
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
  	
  	List<Staff> staffList = (List<Staff>)request.getAttribute("staffList");
   %>
    <h1>显示所有员工信息（经理）</h1>
    <table>
    	<%
    		for (Staff staff : staffList) {
    			
    	 %>
    	 <tr>
    	 <td><%=staff.getS_id() %></td>
    	 <td><%=staff.getS_name() %></td>
    	 <td><%=staff.getDep_id() %></td>
    	 <td><%=staff.getIdentity() %></td>
    	 <td><%=staff.getS_pass() %></td>
    	 <td><a href="manager/managerSelectStaffInfo?e_id=<%=staff.getS_id() %>&e_id=1">选择</a></td>
    	 </tr>
    	 <%
    	 	}
    	  %>
    </table>
</html>
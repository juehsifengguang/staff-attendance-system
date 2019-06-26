<%@page import="com.as.entity.Examination"%>
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
  	
  	List<Examination> examinationList = (List<Examination>)request.getAttribute("examinationList");
   %>
    <h1>显示审批意见表全部内容（经理）</h1>
    <table>
    	<%
    		for (Examination examination : examinationList) {
    			
    	 %>
    	 <tr>
    	 <td><%=examination.getE_id() %></td>
    	 <td><%=examination.getE_comment() %></td>
    	 <td><%=examination.getIs_overtime() %></td>
    	 <td><%=examination.getRecord_id() %></td>
    	 <td><a href="manager/managerSelectExamination?e_id=<%=examination.getE_id() %>&e_id=1">选择</a></td>
    	 </tr>
    	 <%
    	 	}
    	  %>
    </table>
</html>
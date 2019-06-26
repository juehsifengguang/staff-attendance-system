<%@page import="com.as.entity.Overtimeapplication"%>
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
  	List<Overtimeapplication> myOvertimeApplyList = (List<Overtimeapplication>)request.getAttribute("myOvertimeApplyList");
  	Integer s_id = (Integer)request.getAttribute("s_id");
  	
   %>
    <h1>查看自己的所有的加班记录（主管）</h1>
    <table>
    <tr>
    <td>加班开始时间</td>
    <td>加班结束时间</td>
    <td>加班申请原因</td>
    <td>状态</td>
    </tr>
    <%
    	for(Overtimeapplication overtimeApply : myOvertimeApplyList){
    	
     %>
     <tr>
     <td><%=overtimeApply.getOvertime_start() %></td>
     <td><%=overtimeApply.getOvertime_end() %></td>
     <td><%=overtimeApply.getOvertime_reason() %></td>
     <c:if test="<%=overtimeApply.getIs_approved() == 0 %>"><td>未审批</td></c:if>
     <c:if test="<%=overtimeApply.getIs_approved() == 1 %>"><td>已通过</td></c:if>
     <c:if test="<%=overtimeApply.getIs_approved() == 2 %>"><td>未批准</td></c:if>
     </tr>
     <%
    	}
     %>
    </table>
  </body>
</html>
    
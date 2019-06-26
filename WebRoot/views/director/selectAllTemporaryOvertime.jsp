<%@page import="com.as.entity.Temporaryovertime"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'selectAllTemporaryOvertime.jsp' starting page</title>
    
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
  	
  	List<Temporaryovertime> tmpOvertimeNowList = (List<Temporaryovertime>)request.getAttribute("tmpOvertimeNowList");
   %>
    <h1>显示全部可申请的临时加班列表的界面（主管）</h1>
    <table>
    	<%
    		for (Temporaryovertime tmpOvertime : tmpOvertimeNowList) {
    			
    	 %>
    	 <tr>
    	 <td><%=tmpOvertime.getT_overtime_start() %></td>
    	 <td><%=tmpOvertime.getT_overtime_end() %></td>
    	 <td><%=tmpOvertime.getT_o_reason() %></td>
    	 <td><a href="director/applyForTempOvertime?to_id=<%=tmpOvertime.getTo_id() %>&s_id=101">选择</a></td>
    	 </tr>
    	 <%
    	 	}
    	  %>
    </table>
  </body>
</html>
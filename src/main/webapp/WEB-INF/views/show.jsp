<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

String testData = request.getParameter("context");   // 即可获得test_data的值。
out.println(testData);  
%>
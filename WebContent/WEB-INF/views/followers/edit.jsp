<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import= models.Employee %>>
<% Employee fe = (Employee)request.getSession().getAttribute("followed_employee"); %>

<%= fe.getId() %>
<%= fe.getName() %>>

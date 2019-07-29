<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>comic001</title>
</head>
<body>
	<c:forEach items="${pages}" var="Page">
  		<img src="/comic001/comic/${Page.id}/${Page.part}/${Page.content}" alt="${Page.content}">
  	</c:forEach>
</body>
</html>
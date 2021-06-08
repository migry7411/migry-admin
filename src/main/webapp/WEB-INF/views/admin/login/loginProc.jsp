<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>login</title>
<script type="text/javascript" charset="UTF-8">
<!--
window.onload = function() {
	var flag = "${flag}";
	var url = "${pageContext.request.contextPath}/admin/loginPage.do";
	
	if(flag == "1") {
		alert("<spring:message code='messages.member.id.check4' />");
	} else if(flag == "2") {
		alert("<spring:message code='messages.member.password.check' />");
	} else if(flag == "3") {
		alert("관리자 계정이 아닙니다.");
	} else {
		url = "${pageContext.request.contextPath}/admin/main.do";
	}
	
	location.href = url;
};
//-->
</script>
</head>
<body>

</body>
</html>
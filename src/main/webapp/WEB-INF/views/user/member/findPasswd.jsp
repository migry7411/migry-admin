<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<title><spring:message code='contents.findpasswd.title' /></title>
<link rel="stylesheet" href="/resources/css/user/member_frm.css">
<script type="text/javascript">
<!--
function validation(frm) {	
	if(frm.userid.value == "") {
		alert("<spring:message code='messages.member.id' />");
		frm.userid.focus();
		return false;
	}
	
	if(frm.email.value == "") {
		alert("<spring:message code='messages.member.email' />");
		frm.email.focus();
		return false;
	}
	
	return true;
}
//-->
</script>
</head>
<body>
	<h1 class="title"><spring:message code='contents.findpasswd.title' /></h1>
	<div id="frm">
		<form action="findPasswdProc.do" method="post" onsubmit="return validation(this)">
			<table>
				<colgroup>
					<col style="width:120px" />
					<col />
				</colgroup>
				<tr>
					<td><spring:message code='contents.member.id' /></td>
					<td><input type="text" name="userid" /></td>
				</tr>
				<tr>
					<td><spring:message code='contents.member.email' /></td>
					<td><input type="email" name="email" /></td>
				</tr>
			</table>
			<div class="btn"><button type="submit"><spring:message code='contents.common.confirm' /></button> <button type="reset"><spring:message code='contents.common.cancel' /></button></div>
		</form>
	</div>
</body>
</html>
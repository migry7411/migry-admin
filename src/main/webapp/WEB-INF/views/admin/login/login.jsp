<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String root = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
<link rel="stylesheet" href="/resources/css/admin/login.css">
<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<script type="text/javascript" src="/resources/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
function loginValidation(frm) {
	if(frm.user_id.value == "") {
		alert("아이디를 입력하세요.");
		frm.user_id.focus();
		return false;
	}
	
	if(frm.user_pw.value == "") {
		alert("비밀번호를 입력하세요.");
		frm.user_pw.focus();
		return false;
	}
	
	return true;
}
</script>
</head>
<body>
	<div id="wrapper">
		<div id="body_wrapper">
			<form action="<%= root %>/admin/loginProc.do" method="post" onsubmit="return loginValidation(this)">
				<fieldset>
					<legend>로그인 폼</legend>
					<p>
						<label for="user_id">ID</label>
						<input type="text" id="user_id" name="userid" required placeholder="아이디">
					</p>
					<p>
						<label for="user_pw">PW</label>
						<input type="password" id="user_pw" name="passwd" required placeholder="비밀번호">
					</p>
					<button type="submit">로그인</button>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>
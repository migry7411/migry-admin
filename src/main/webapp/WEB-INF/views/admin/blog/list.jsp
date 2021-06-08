<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 관리</title>
<link rel="stylesheet" href="/resources/css/admin/blog.css">
<script type="text/javascript" charset="UTF-8">
<!--
function deleteBlog(id) {
	if(confirm("<spring:message code='messages.common.delete' />")) {
		var url = "deleteBlog.do?id=" + id;
		location.href = url;
	}
}

function openImage(width, height, fileName, id) {
	openModal(width+20, height+20);
	var src = "";
	
	if(fileName == "noimage.png") {
		src = "/resources/images/" + fileName;
	} else {
		src = "${pageContext.request.contextPath}/admin/blog/getImage.do?id=" + id;
	}
	
	var html = "<div class='img'><a href='javascript:closeModal()'><img src='" + src + "' border='0' /></a></div>";
	$("#modal").html(html);
}
//-->
</script>
</head>
<body>
	<h1>블로그 관리</h1>
	<div class="frm">
		<form action="insertBlog.do" method="post" enctype="multipart/form-data">
			<fieldset>
				<legend>블로그 입력 폼</legend>
				<table>
					<tr>
						<td><label for="blog_subject"><spring:message code="contents.common.subject" /></label></td>
						<td><input type="text" id="blog_subject" name="title" required></td>
					</tr>
					<tr>
						<td><label for="blog_contents"><spring:message code="contents.common.content" /></label></td>
						<td><textarea id="blog_contents" name="content" required></textarea></td>
					</tr>
					<tr>
						<td><label for="blog_file">첨부 이미지</label></td>
						<td><input type="file" id="blog_file" name="uploadFile"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit"><spring:message code="contents.common.confirm" /></button>
							<button type="reset"><spring:message code="contents.common.cancel" /></button>
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<c:forEach var="row" items="${list}">
	<div class="list">
		<ul>
			<li>${row.title}</li>
			<li>${row.rgstDt}</li>
			<li>${row.content}</li>
			<li><a href="javascript:openImage(${row.width}, ${row.height}, '${row.fileName}', ${row.id})">&nbsp;${row.oriFileName}</a></li>
			<li><button type="button" onclick="deleteBlog(${row.id})"><spring:message code="contents.common.delete" /></button></li>
		</ul>
	</div>
	</c:forEach>
	
	<div class="paging">${paging}</div>
	
</body>
</html>
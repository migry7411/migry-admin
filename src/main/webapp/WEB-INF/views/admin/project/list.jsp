<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 관리</title>
<link rel="stylesheet" href="/resources/css/admin/project.css" />
<script type="text/javascript" charset="UTF-8">
<!--
var num = 0;

function selectRow(n, id) {
	$("#row" + num).css("background-color", "transparent");
	$("#row" + n).css("background-color", "#333");
	num = n;
	
	$.ajax({
		type: 'POST'
 		, dataType: 'JSON'
 		, async: true
 		, url: 'detailProject.do'
 		, data: { "id" : id }
 		, success: function(data) {
 			$("#id").val(data.result.id);
 			$("#title").val(data.result.title);
 			$("#company").val(data.result.company);
 			$("#content").val(data.result.content);
 			$("#startYm").val(data.result.startYm);
 			$("#endYm").val(data.result.endYm);
		}
		, error: function(data, status, err) {
			alert(err);
		}
	});
}

function deleteProject() {
	var id = $('#id').val();
	if(!id) {
		alert("삭제할 프로젝트를 선택하시오.");
		return;
	}
	
	if(confirm("<spring:message code='messages.common.delete' />")) {
		var url = "deleteProject.do?id=" + id;
		location.href = url;
	}
}

function idcheck(frm) {
	if(frm.id.value == "") {
		frm.id.value = "0";
	}
	
	return true;
}
//-->
</script>
</head>
<body>
	<h1>프로젝트 관리</h1>
	<div class="list">
		<div class="row_header">
			<ul>
				<li>프로젝트명</li>
			</ul>
		</div>
		
		<c:if test="${count < 1}">
		<div class="rows_empty"><spring:message code="messages.common.list.empty" /></div>
		</c:if>
		
		<c:if test="${count > 0}">
		
		<c:set var="num" value="1" />
			
		<c:forEach var="row" items="${list}">
		<div id="row${num}" class="rows" onmousedown="selectRow(${num}, '${row.id}')">
			<ul>
				<li>${fn:replace(fn:replace(fn:replace(row.title, '<', '&lt;'), '>', '&gt;'), '&', '&amp;')}</li>
			</ul>
		</div>
		<c:set var="num" value="${num + 1}" />
		</c:forEach>
		
		</c:if>
	</div>
	<div class="detail">
		<form action="saveProject.do" method="post" onsubmit="return idcheck(this)">
			<fieldset>
				<legend>프로젝트 정보</legend>
				<p>
					<label for="id" class="lbl">ID</label>
					<span><input type="text" id="id" name="id" readonly="readonly"></span>
				</p>
				<p>
					<label for="title" class="lbl">프로젝트명</label>
					<span><input type="text" id="title" name="title" required></span>
				</p>
				<p>
					<label for="company" class="lbl">업체명</label>
					<span><input type="text" id="company" name="company" required></span>
				</p>
				<p>
					<label for="content" class="lbl">프로젝트 내용</label>
					<textarea id="content" name="content" required></textarea>
				</p>
				<p>
					<label for="startYm" class="lbl">프로젝트 기간</label>
					<span><input type="text" id="startYm" name="startYm" class="ym"> ~ <input type="text" id="endYm" name="endYm" class="ym"></span>
				</p>
				<button type="reset"><spring:message code="contents.common.add" /></button>
				<button type="submit"><spring:message code="contents.common.save" /></button>
				<button type="button" onclick="deleteProject()"><spring:message code="contents.common.delete" /></button>
			</fieldset>
		</form>
	</div>
</body>
</html>
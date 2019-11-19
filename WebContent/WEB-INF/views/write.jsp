<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="./layout/msgdiv.jsp"></jsp:include>
	<h2>글쓰기</h2>
	<form action="/board/write" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">제목 </label>
			<input type="text" name="title" id="title" placeholder="제목을 입력하세요">
		</div>
		<div class="form-group">
			<label for="content">글내용</label>
			<textarea rows="10" cols="30" name="content" id="content" placeholder="글내용"></textarea>
		</div>
		<div class="form-group">
			<label for="file">파일첨부</label>
			<input type="file" name="file" id="file">
		</div>
		<input type="submit" value="글쓰기">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/MISSION/email_servlet/send.do">
발신자이름:<input name="sender_name"><br>
발신자이메일:<input name="sender_mail"><br>
수신자이메일:<input name="receive_mail"><br>
제목:<input name="subject"><br>
내용:<textarea rows="5" cols="80" name="message"></textarea>><br>
<input type="submit" value="전송">
</form>
<c:if test="${param.message =='OK' }">
	<span style ="color:red">이메일 발송완료!</span>
</c:if>
</body>
</html>

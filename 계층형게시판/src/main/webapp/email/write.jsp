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
�߽����̸�:<input name="sender_name"><br>
�߽����̸���:<input name="sender_mail"><br>
�������̸���:<input name="receive_mail"><br>
����:<input name="subject"><br>
����:<textarea rows="5" cols="80" name="message"></textarea>><br>
<input type="submit" value="����">
</form>
<c:if test="${param.message =='OK' }">
	<span style ="color:red">�̸��� �߼ۿϷ�!</span>
</c:if>
</body>
</html>

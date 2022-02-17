<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList,mvc.domain.Login" %>
<meta charset='utf-8'>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
session.invalidate(); // 세션 초기화
%>
<script>
alert("로그아웃 되었습니다.");
location.href='../';
</script>
</body>
</html>
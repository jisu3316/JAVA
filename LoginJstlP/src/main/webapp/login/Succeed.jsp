<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList,mvc.domain.Login" %>
<meta charset='utf-8'>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test ="${empty idlist}">
			<center>
			<tr>		
			<td colspan="5" style ="text-align:center">아이디가없네요 회원가입해주세요</td>
			<a href='../login/join.jsp'>회원가입</a>
			</tr>
			</center>
</c:if>

<c:forEach items = "${idlist}" var ="login">
<c:choose>	
	<c:when test ="${id ne login.id}">
			<script>
					alert("아이디가 다릅니다,아이디가없으시면 회원가입해주세요11");
					location.href='login.jsp';
			</script>
			return;
	</c:when>
	<c:when test ="${pw ne login.pw}">
			<script>
					alert("비밀번호가 다릅니다");
					location.href='login.jsp';
			</script>
			return;
	</c:when>
	<c:when test ="${id eq login.id && pw eq login.pw}">
			<script>
					alert("로그인성공");
					location.href = "../";
			</script>
			
	</c:when>
	</c:choose>
</c:forEach>




		




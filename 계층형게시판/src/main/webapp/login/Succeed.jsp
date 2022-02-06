<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList,mvc.domain.Member" %>
<meta charset='utf-8'>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test ="${empty emaillist}">
			<center>
			<tr>		
			<td colspan="5" style ="text-align:center">아이디가없네요 회원가입해주세요</td>
			<a href='../login/join.jsp'>회원가입</a>
			다시로그인하시겠습니까?<a href='login.jsp'>로그인</a>
			</tr>
			</center>
</c:if>
<c:forEach items = "${emaillist}" var ="Mem">
<c:choose>	
	<c:when test ="${id ne Mem.email}">
			<script>
					alert("이메일이 다릅니다,아이디가없으시면 회원가입해주세요11");
					location.href='login.jsp';
			</script>			
	</c:when>
	<c:when test ="${pwd ne Mem.pwd}">
			<script>
					alert("비밀번호가 다릅니다");
					location.href='login.jsp';
			</script>
			return;
	</c:when>
	<c:when test ="${id eq Mem.email && pwd eq Mem.pwd}">
			<script>
					alert("로그인성공");
					opener.parent.location.reload();
					window.close();						
			</script>			
	</c:when>
	</c:choose>
</c:forEach>




		




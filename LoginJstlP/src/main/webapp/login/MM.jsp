<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList,mvc.domain.SubList,mvc.domain.Login, login.mvc.model.LoginService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<meta charset='utf-8'>
<style>
	table, th, td {
	   border: 1px solid black;
	   border-collapse: collapse;
	}
	th, td {
	   padding: 5px;
	}
	a { text-decoration:none }
</style>


<h3>내 아이디: ${id }</h3>


<br>
<br>
<a href='../'>인덱스</a>
<table border='1' width='600' align='center' cellpadding='2'>
<tr>
	<th align='center' width='10%'>번호</th>
	<th align='center' width='15%'>nickname</th>
	<th align='center' width='15%'>id</th>
	<th align='center' width='15%'>pw</th>
	<th align='center' width='30%'>성별</th>
	<th align='center' width='15%'>날짜</th>
	<th align='center' width='15%'>삭제</th>
</tr>


<c:if test="${empty list}">
			<tr>
				<td colspan="6" style ="text-align:center">데이터가 하나도없네요</td>	
	   		</tr>
</c:if>

<c:forEach items = "${list}" var = "Login">
	<tr>
	<td align='center'>${Login.seq}</td>
	<td align='center'>${Login.nickname}</td>
	<td align='center'>${Login.id}</td>
	<td align='center'>${Login.pw}</td>
	<td align='center'>${Login.gender}</td>
	<td align='center'>${Login.rdate}</td>
	<td align='center'>
	<a href='login.do?m=del&seq=${Login.seq}'>삭제</a></td>
	</tr>
</c:forEach>
		
		
</table>
<hr width='600' size='2' noshade>


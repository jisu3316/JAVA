<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList,mvc.domain.SubList" %>
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

<h3>내아이디:${id }</h3>
<center>
<hr width='600' size='2' noshade>
<h2>CONTENT JSTL+EL</h2>
&nbsp;&nbsp;&nbsp;
<a href='list.do?m=input'>글쓰기</a>
<hr width='600' size='2' noshade>
<table border='1' width='600' align='center' cellpadding='3'>

<c:if test ="${empty content}">
			<tr>
				<td colspan="5" style ="text-align:center">데이터가 하나도없네요</td>				
			</tr>
</c:if>

<c:forEach items = "${content}" var ="SubList">
<c:if test ="${seq eq SubList.seq}">
				<tr>
					<td width='100' align='center'>글번호</td>
					<td>${SubList.seq}</td>
					</tr>
					<tr>
					<td align='center'>글쓴이</td>
					<td>${SubList.id }</td>
					</tr>
					<tr>
					<td align='center'>닉네임</td>
					<td>${SubList.nickname }</td>
					</tr>
					<tr>
					<td align='center'>글제목</td>
					<td>${SubList.subject }</td>
					</tr>
					<tr>
					<td align='center'>글내용</td>	
					<td>${SubList.content }</td>
				</tr>
				</table>
<hr width='600' size='2' noshade>
<b>
<a  href='list.do?m=update&seq=${SubList.seq }&id=${SubList.id}'>수정</a>
 | 
<a href='list.do?m=del&seq=${SubList.seq }&id=${SubList.id}'>삭제</a>
 | 
<a href='list.do'>목록</a>
</b>
<hr width='600' size='2' noshade>
</c:if>
</c:forEach>


</center>



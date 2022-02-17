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
<c:if test ="${empty update}">
			<tr>
				<td colspan="5" style ="text-align:center">데이터가 하나도없네요</td>				
			</tr>
</c:if>
<c:forEach items = "${update}" var ="SubList">
<c:if test ="${seq eq SubList.seq}">
</style>
				<body onload='javascript:document.f.email.focus();'>
				<center>
				<hr width='600' size='2' noshade>
				<h2>JSTL+EL update</h2>
				<a href='list.do'>글목록</a>
				<hr width='600' size='2' noshade>
					
				<form name='f' method='post' action='list.do?m=update2&seq=${SubList.seq }'>
				<table border='1' width='600' align='center' cellpadding='3' cellspacing='1'><tr>
				<td width='30%' align='center'>글쓴이</td>
					<td align='center'><input type='text' name='ID' size='60' value=${SubList.id } disabled></td>
					</tr>
					 <tr>
					 <td width='30%' align='center'>닉네임</td>
					 <td align='center'><input type='text' name='NICKNAME' size='60' value=${SubList.nickname } disabled></td>
					 </tr>
					 <tr>
					 <td width='30%' align='center'>글제목</td>
					 <td align='center'><input type='text' name='subject' size='60' value=${SubList.subject }></td>
					 </tr>
					 <tr>
					 <td width='30%' align='center'>글내용</td>
					 <td align='center'><textarea name='content' rows='5' cols='53'>${SubList.content }</textarea></td>
					 </tr>
					 <tr>
					 <td colspan='2' align='center'>
					 <input type='submit' value='수정'>
					 </td>
			 			</tr>
					 </table>
</c:if>
</c:forEach>			
</form>
</body>

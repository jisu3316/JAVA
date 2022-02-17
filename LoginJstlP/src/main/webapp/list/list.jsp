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


<center>
	<hr width='600' size='2' noshade>
	<h2>list JSTL </h2>
	&nbsp;&nbsp;&nbsp;
	<form name="input" method="post" action="list.do?m=like">
	<label for ="search">검색</label>
        <select id="search" name="search" size="1">
            <option value="">선택하세요</option>
            <option value="nickname">nickname</option>
            <option value="id">id</option>
            <option value="subject">글제목</option>
        </select>
        <input type="text" name="searchtext" ><input type="submit" value="전송">
        </form>
	<a href='list.do?m=input'>글쓰기</a>
	&nbsp;&nbsp;&nbsp;
	<a href='../'>인덱스</a>
	<hr width='600' size='2' noshade>
</center>
<table border='1' width='600' align='center' cellpadding='2'>
<tr>
	<th align='center' width='10%'>번호</th>
	<th align='center' width='15%'>nickname</th>
	<th align='center' width='15%'>id</th>
	<th align='center' width='30%'>글제목</th>
	<th align='center' width='15%'>날짜</th>
</tr>


<c:if test="${empty list}">
			<tr>
				<td colspan="5" style ="text-align:center">데이터가 하나도없네요</td>	
	   		</tr>
</c:if>
<c:if test="${list ne null}">
	<c:set var="size" value="${list }"/>
	<c:if test ="${size eq null }">
			<tr>
				<td colspan="5" style ="text-align:center">데이터가 하나도없네요</td>				
			</tr>
	</c:if>
</c:if>
<c:forEach items = "${list}" var = "SubList">
	<tr>
	<td align='center'>${SubList.seq}</td>
	<td align='center'>${SubList.nickname}</td>
	<td align='center'>${SubList.id}</td>
	<td align='center'>
	<a href='list.do?m=content&seq=${SubList.seq}'>${SubList.subject}</a></td>
	<td align='center'>${SubList.rdate}</td>
	</tr>
</c:forEach>
		
		
</table>
<hr width='600' size='2' noshade>
회원목록

<% 
LoginService service = LoginService.getInstance();
ArrayList<Login> lolist = service.listS();
for(Login dto : lolist){
%>
	
	<ul > 
		<li><%=dto.getId()%></li>	
	</ul>
<% 
}
%>

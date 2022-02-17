<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList,mvc.domain.SubList,mvc.domain.Login, login.mvc.model.LoginService" %>



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
<% 
String userid =(String)session.getAttribute("id");

%>
<h3>내 아이디: <%= userid%></h3>


<br>
<br>



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
<center>
	<hr width='600' size='2' noshade>
	<h2>list MVC </h2>
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

<%	
			String searchtext=(String)request.getAttribute("searchtext");
			ArrayList<SubList> list =(ArrayList<SubList>)request.getAttribute("list");
			if(list ==null){
%>
		<tr>
		<td align='center' colspan="5">서버에 문제 발생</td>
	   </tr>

<%				
				
			}

			if(list != null){
				int size = list.size();
				if(size==0){
%>			<tr>
				<td colspan="5" style ="text-align:center">데이터가 하나도없네요</td>				
			</tr>
<%
					
				}else{
					for(SubList dto : list){					
%>
<tr>
	<td align='center'><%=dto.getSeq()%></td>
	<td align='center'><%=dto.getNickname()%></td>
	<td align='center'><%=dto.getId()%></td>
	<td align='center'>
	<a href='list.do?m=content&seq=<%=dto.getSeq()%>'><%=dto.getSubject()%></a></td>
	<td align='center'><%=dto.getRdate()%></td>
	
	</td>
	
</tr>



<%
				}
			}
		}
%>
		
		
</table>
<hr width='600' size='2' noshade>


<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList,mvc.domain.Memlist, mission.mvc.model.LoginService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="http://image.lgeshop.com/css/style_2005.css">
<!DOCTYPE html>
<html>
  <head> 
    <title>reboard_list_partSel.jsp</title>
  </head>
  <body>
    <center>
	  <hr width="600" color="Maroon" size="2" noshade>
	    <font size="5" color="Navy">
		  <b>지수 board</b>
		</font>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="list.do?m=write">글쓰기</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href='../'>메인</a>
		
		<form name="f"  method="post" action="list.do?m=list">
		  <!--  <input type="hidden"  name="method" value="list">-->
		<select name="ps" onChange="submit()" size="1">
				<option value="" selected>페이지SIZE선택</option>
				<option value=5>페이지 SIZE 5</option>
				<option value=10>페이지 SIZE 10</option>
				<option value=15>페이지 SIZE 15</option>
				<option value=20>페이지 SIZE 20</option>
		  </select>
		</form>
	</center>
<%
	int total=(int)request.getAttribute("total");
	int pageselect=(int)request.getAttribute("pageselect");
	int lastpage = (int)Math.ceil((double)total/pageselect);
	
%>	
	  <hr width="600" color="Maroon" size="2" noshade>

	  <table align="center" cellspacing="1" cellpadding="3" 
	                                   width="600" border="1">
		<tr align="center">
		  <td width="10%">
		    <b>순번</b>
		  </td>
		  <td width="40%"><b>제목</b></td>
		  <td width="15%"><b>글쓴이</b></td>
		  <td width="15%"><b>날짜</b></td>
		  <td width="20%"><b>조회수</b></td>
		  <td width="20%"><b>ref</b></td>
		  <td width="20%"><b>step</b></td>
		  <td width="20%"><b>depth</b></td>
		</tr>
<c:if test="${empty list}">

			<tr>
				<td colspan="5" style ="text-align:center">데이터가 하나도없네요</td>	
	   		</tr>
</c:if>

<c:forEach items = "${list}" var = "Memlist">
		<tr align="center">
				  <td width="10%">${Memlist.seq }</td>
				  <td width="40%" align="left"> 
		            <a href='list.do?m=content&seq=${Memlist.seq}&views=${Memlist.views}'>
                      ${Memlist.subject }
                    </a>
				  </td>
				  <td width="15%">		    
                      ${Memlist.name }
				  </td>
				  <td width="15%">${Memlist.rdate}</td>			 
				  <td width="20%">${Memlist.views}</td>
				  <td width="20%">${Memlist.ref}</td>
				  <td width="20%">${Memlist.step}</td>
				  <td width="20%">${Memlist.depth}</td>
				</tr>
		</tr>
</c:forEach>
<td colspan="2" align="light">
		    총 게시물 수 : <%=total %>  
		  </td>
	  </table>
		<hr width="600" color="Maroon" size="2" noshade>
		<div width="600px" align="center" margin-top="10px">
		<%
		for(int i = 1; i<=lastpage;i++){
		%>
			<a href="list.do?m=list&ps=<%=pageselect%>&vpage=<%=i%>"><%=i %></a>
		<%
		}
		%>
		</div>
	  
  </body>
</html>


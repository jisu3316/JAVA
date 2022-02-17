<%@ page contentType="text/html;charset=utf-8" import="java.sql.*,java.util.ArrayList,mvc.domain.Login"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>간단한 게시판</title>
	<meta charset='utf-8'>
	<script >
	   function check()
	   {
	       for(var i=0; i<document.input.elements.length; i++)
		   {
		      if(document.input.elements[i].value == "")
			  {
			     alert("모든 값을 입력 하셔야 합니다. ");
				 return false;
			  }
		   }
		   document.input.submit();
       }
	</script>
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

<h3>내아이디:${id}</h3>
  </head>
  <body onload="input.name.focus()">

<c:forEach items = "${list}" var ="Login">
<c:if test ="${id eq Login.id}">
<center>
	   <hr width="600" size="2" noshade>
	      <h2>JSP Input MVC</h2>
		  <a href='list.do'>글목록</a>
	   <hr width="600" size="2" noshade>
	</center>
	
	<form name="input" method="post" action="list.do?m=listinsert">
	 <input type="hidden" id="Id" name="ID" value=${Login.id }>
	 <input type="hidden" id="NN" name="NICKNAME" value=${Login.nickname }>
	   <table border="1" width="600" align="center"  cellpadding="3" cellspacing="1">
	      <tr>
		     <td align="center">닉네임</td>
			 <td align="center"><input type='text' name='nickname' size='60' value =${Login.nickname} disabled></td>
		  </tr>   
	      <tr>
		     <td width="30%" align="center">글쓴이</td>
			 <td align='center'><input type='text' name='id' size='60' value=${Login.id} disabled></td>
		  </tr>		  
          <tr>
		     <td align="center">글제목</td>
			 <td align="center"><input type="text" name="subject" size="60"></td>
		  </tr>
		  <tr>
		     <td align="center">글내용</td>
			 <td align="center"><textarea name="content" rows="5" cols="53"></textarea></td>
		  </tr>

</c:if>
</c:forEach>
 <tr>
		     <td colspan="2" align="center">
			    <input type="button" value="전송" onclick="check()">
				<input type="reset" value="다시입력">
			 </td>
		  </tr>
	   </table>
	   <br>
	   <hr width="600" size="2" noshade>
	</form>
  </body>
</html>

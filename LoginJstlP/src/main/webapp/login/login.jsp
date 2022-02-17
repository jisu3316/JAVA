<%@ page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<html>
  <head>
    <title>간단한 게시판</title>
	<meta charset='utf-8'>
	<script language="javascript">
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
  </head>
  <body onload="input.name.focus()">
    <center>
	   <hr width="600" size="2" noshade>
	      <h2>로그인</h2>
	   <hr width="600" size="2" noshade>
	   <a href='../'>홈페이지</a>
	   <center>
 <a href='../login/join.jsp'>회원가입</a>
 </center>

	</center>
	<form name="input" method="post" action="login.do?m=login">
	
	   <table border="1" width="600" align="center"  cellpadding="3" cellspacing="1">
	      <tr>
		     <td width="30%" align="center">아이디</td>
			 <td align="center"><input type="text" name="id" size="60"></td>
		  </tr>
		  <tr>
		     <td align="center">비밀번호</td>
			 <td align="center"><input type="password" name="pw" size="60"></td>
		 </tr>		 
		  <tr>
		     <td colspan="2" align="center">
			    <input type="button" value="전송" onclick="check()">
				<input type="reset" value="다시입력">
			 </td>
		  </tr>
	   </table>
	   <script>
	   const $input = document.querySelector('input');
	   $input.focus();
	   </script>
	   <br>
	   <hr width="600" size="2" noshade>
	</form>
  </body>
</html>
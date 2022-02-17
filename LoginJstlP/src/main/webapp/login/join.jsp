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
	      <h2>회원가입</h2>
	      <a href='../'>인덱스</a>
	   <hr width="600" size="2" noshade>
	</center>
	<form name="input" method="post" action="../login/login.do?m=insert">
	   <table border="1" width="600" align="center"  cellpadding="3" cellspacing="1">
	      <tr>
		     <td align="center">닉네임</td>
			 <td align="center"><input type="text" name="nickname" size="60"></td>
		  </tr>
	      <tr>
		     <td width="30%" align="center">아이디</td>
			 <td align="center"><input type="text" name="id" size="60"></td>
		  </tr>
		  <tr>
		     <td align="center">비밀번호</td>
			 <td align="center"><input type="password" name="pw" size="60"></td>
		  </tr>
		  <tr>
		     <td align="center">성별</td>
			 <td colspan='2' align="center">			
			 <label><input type="radio" name="gender"  value ="남"size="60" >남</label>
			 <label><input type="radio" name="gender"  value ="여"size="60" >여</label></td>		 
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
<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList,mvc.domain.Memlist" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="http://image.lgeshop.com/css/style_2005.css">
<html>
  <head>
    <title>reboard_content.jsp</title>
  </head>

  <body>
    
	  <hr width="600" color="Maroon" size="2" noshade>
	    <font size="5" color="Navy">
		  <b>지수 board</b>
		</font>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <a href="list.do?m=list">목록</a>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <a href='../'>메인</a>
		  
	  <hr width="600" color="Maroon" size="2" noshade>


	  <table align="center" width="600" cellspacing="1" 
	                          cellpadding="3" border="1" id="table">
<c:if test ="${empty content}">
			<tr>
				<td colspan="5" style ="text-align:center">데이터가 하나도없네요</td>				
			</tr>
</c:if>	
<c:forEach items = "${content}" var ="Memlist">
<c:if test ="${seq eq Memlist.seq}">	
		
		<tr>
		  <td align="center" width="15%"><b>순번</b></td>
		  <td align="center" width="35%">&nbsp;${Memlist.seq }</td>
		  <td align="center" width="15%"><b>날짜</b></td>
		  <td align="center" width="35%">
			&nbsp;${Memlist.rdate }
		  </td>
		</tr>
		<tr>
		  <td align="center" width="15%"><b>글쓴이</b></td>
		  <td align="center" width="35%">
			&nbsp; ${Memlist.name }
	      </td>
		  <td align="center" width="15%"><b>HomePage</b></td>
		  <td align="center" width="35%">
		    &nbsp;${Memlist.homepage }
		  </td>
		</tr>
		<tr>
		  <td colspan="3">&nbsp;</td>
		  <td><b>조회수</b> : ${Memlist.views }</td>
		</tr>
		<tr>
		  <td colspan="2"><b>제목</b> : ${Memlist.subject }</td>
		  <td colspan="2"><b>첨부파일</b>:

		  </td>
		</tr>
		<tr>

		  <td colspan="4">ass</td>
		</tr>
		<tr>
		  <td colspan="4" align="center">
		    <hr width="550" color="Maroon" size="2" noshade>
			<!----------------- re 변화 2 ---------------->
			<a href="rb-list.do?method=list">목록</a> | 
			<a href="rb-edit.do?method=edit">편집</a> | 
			<a href="rb-del.do?method=del">삭제</a> | 
			<a href="list.do?m=answervalue&$seq=${Memlist.seq}&ref=${Memlist.ref}&step=${Memlist.step}&depth=${Memlist.depth}">답변</a>
		    <!-- <a href=
	"reboard_rewrite.jsp?idx=13&ref=9&lev=0&sunbun=0&cp=1">
			  답변
		    </a> -->
			
			<!-------------------------------------------->
		  </td>
		</tr>
		<tr align="center" id="ta">
		  <td>
		     리플달기 
		  </td>

          <script language="javascript">
              loginJs = false;
              function check()
              {
                  if(!loginJs)
                  {
                      yesNo = confirm(
                         "먼저 로그인을 하셔야 합니다. 로그인 페이지로 이동할까요?");
                      if(yesNo)
                      {
                         location.href="/M2Project/login.do";
  
                      }
                      
                      return;
                  }
                  else
                  {
                      if(f1.content_reply.value == "" || f1.pwd_reply.value == "")
	                  {
	                      alert("리플 내용과 비밀번호를 모두 입력하셔야 합니다.");
	                      return;
	                  }
                  	  f1.submit();
                  }
              }
          </script>
</c:if>
</c:forEach>
		  <form name="f1" action="rb-reply-save.do">
			  <td colspan="3">
			     <input type="hidden" name="method" value="replySave">
				 <input type="text" name="content_reply" size="50">
				 &nbsp;&nbsp;비밀번호
				 <input type="text" name="pwd_reply" size="10">
				 <input type="button" value="등록" onclick="check()">
			  </td>
		  </form>
		</tr>
	  </table>
	  
	  <br><hr width="600" color="Maroon" size="2" noshade><br>
	  
	  
<script language="javascript">     
      function replyDelCheck(idx_reply)
      {
          //alert("loginJs : " + loginJs);
          //alert("idx_reply : " + idx_reply);
          if(!loginJs) //로그인 안함 
          {
             
             alert("로그인 or 리플의 비밀번호를 입력하셔야 합니다.");
             yesNo = confirm("로그인을 하시겠습니까?");
             if(yesNo)
             {
                  location.href="/M2Project/login.do";   
      
             }
             else
             {
                   pwd_reply = prompt("리플 비밀번호를 입력해주세요..");
                   location.href ="/M2Project/rb-reply-del.do?method=replyDel"
                        +"&idx_reply="+idx_reply+"&pwd_reply="+pwd_reply;
             }
          }
          else  // 로그인 함 
          {
               location.href ="/M2Project/rb-reply-del.do?method=replyDel"
                        +"&idx_reply="+idx_reply;
          }
      }
</script>

      <table align="center" width="600" cellspacing="1" 
	                          cellpadding="3" border="1"> 
		 <tr align="center">
		     <td colspan="2">
			    <font color="red"><b>R E P L Y</b></font>
			 </td>
		 </tr>
	  </table>
	</center>

  </body>
</html>
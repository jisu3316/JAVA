<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8"/>
		<title>Ajax Test02</title>
		<script type="text/javascript" language="javascript" 
		     src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
		<script type="text/javascript">
			/*	
			$(document).ready(function(){	
			});*/
			$(function(){
				$("#seq").on("keyup",function(){//on~할때 ()안에 이벤트를 넣어준다.키업이 될때 펑션을 실행한다
					$.ajax({
						url: "../ajax02/search01.do",
						type: "GET",
						data: {seq: $("#seq").val()},
						success: function(data){
							//var jsOBJ = JSON.parse(data);//json ->jsObj 제이슨을 자바스크립트로 바꿔준다
							//console.log("1");
							console.log("#data: "+data.writer);
							
							//(2) 화면갱신 
							 if(!data){
		    					 alert("존재하지 않는 SEQ");
		    					 return false;
		    				 }
							
							 var html = "";
		    				 html += "<form id='ajax'>";
		    				 html += "번호 <input name='seq' value='"+data.seq+"'>";
		    				 html += "이름<input name='writer' value='"+data.writer+"'>";
		    				 html += "이메일 <input name='email' value='"+data.email+"'>";
		    				 html += "글내용<input name='content' value='"+data.rdate+"'>";
		    				 html += "</form>";
		    				 
		    				 $("#name").val("");
		    				 $("#container").html(html);
						}
					});
				});
				$("#searchOk02").on("click",function(){
					$.ajax({
						url: "../ajax02/search02.do",
						type: "POST",
						data: {writer: $("#name").val()},
						success: function(data){
							if(!data){
								alert("존재하지 않는 name");
								return false;
							}
							
							let html = "";
							html += "<table border ='1' width='50%'>";
							html += "<tr>";
							html += "<th>번호</th>";
							html += "<th>이름</th>";
							html += "<th>이메일</th>";
							html += "<th>글내용</th>";
							html += "</tr>";
							
							if(data.lenth == 0){
								html += "<tr>";
								html += "<td colspan='4' align='center'>그런 이름 가진 회원이 없습니다</td>";
								html += "</tr>";
							}else{
								for(let board of data){
									html += "<tr>";
									html += "<td align='center'>"+board.seq+"</td>";
									html += "<td align='center'>"+board.writer+"</td>";
									html += "<td align='center'>"+board.email+"</td>";
									html += "<td align='center'>"+board.rdate+"</td>";
									html += "</tr>";
								}
							}
							html += "</table>";
							
							$("#seq").val("");
		    				$("#container").html(html);
						}
					});
				});
			});
		</script>
	</head>
	<body>
	    <center>
	    <h2>(방법2) ObjectMapper</h2>
	    
	    번호: <input type="text" name="seq" id="seq"/>
	    <input type="button" value="번호 검색" id="searchOk01"/>
	    <br/>
	    
	    이름: <input type="text" name="writer" id="name"/>
	    <input type="button" value="이름 검색" id="searchOk02"/>
	 
        <br/><br/>
		<div id="container"></div>
		<br/><br/>
		
		<a href="../">인덱스</a><br/><br/>
		</center>
	</body>
</html>
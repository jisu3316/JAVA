<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



	<script>
					if(${flag}){
                    	alert("회원가입성공(JSTL+EL)");
                    	alert("로그인 창으로 이동합니다(JSTL+EL)");
                    	location.href='login.jsp';
                    }else{
                    	alert("회원가입실패(중복된 아이디입니다.(JSTL+EL))");
                    	location.href='join.jsp';
                    }
                    

</script>
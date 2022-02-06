<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




	<script>
					if(${flag}){
                    	alert("글 작성 성공(JSTL+EL)");
                    }else{
                    	alert("글 작성 실패(JSTL+EL)");
                    }
                    location.href='list.do';

</script>
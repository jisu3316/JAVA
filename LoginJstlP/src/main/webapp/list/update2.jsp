<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

s
<script>
					if(${flag}){
                    	alert("수정성공(JSTL+EL)");
                    }else{
                    	alert("수정실패(JSTL+EL)");
                    }
                    location.href='list.do';
</script>
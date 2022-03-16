package team1.togather.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import team1.togather.domain.Member;

public class LoginInterceptor implements HandlerInterceptor {

	// controller로 보내기 전에 처리하는 인터셉터
	// 반환이 false라면 controller로 요청을 안함
	// 매개변수 Object는 핸들러 정보를 의미한다. ( RequestMapping , DefaultServletHandler ) 
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        System.out.println("preHandle1");
        System.out.println("프리핸들러");
        
        HttpSession session = request.getSession();
        Member m = (Member)session.getAttribute("m");
        if(m==null) {
        	response.sendRedirect("../member/login.do");
        	return false;
        }
        
        return true;
    }
	// controller의 handler가 끝나면 처리됨
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
        System.out.println("postHandle1");
        
        
    }
 
 // view까지 처리가 끝난 후에 처리됨
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        
        System.out.println("afterCompletion1");
        
        
    }    

}

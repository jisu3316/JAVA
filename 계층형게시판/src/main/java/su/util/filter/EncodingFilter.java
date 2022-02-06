package su.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class EncodingFilter implements Filter {
	
	private String encoding;
	protected FilterConfig filterConfig;
		
	
	public void init(FilterConfig filterConfig) //이닛파라미터로 web.xml param랑 일치하면 벨류가 나옴.
		throws ServletException {
		this.filterConfig=filterConfig;
		this.encoding=filterConfig.getInitParameter("encoding");		
	}
	/*
	 * HttpServletRequest 객체에 web.xml에서 전달된 문자  인코딩을 설정하는 메소드.
	 */	 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
		throws IOException,ServletException {
		if(request.getCharacterEncoding()==null) {
			if(encoding!=null) {
				request.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request,response);		
	}
	/*
	 *  web.xml에서 전달된 인코딩 값을 초기화하는 메소드
	 */	
	public void destroy() {
		this.encoding=null;
		this.filterConfig=null;		
	}	
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}
	
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig=filterConfig;
	}
}//end of class

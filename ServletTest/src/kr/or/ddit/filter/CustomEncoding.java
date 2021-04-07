package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter를 이용한 한글 인코딩 처리 예제 
 */
public class CustomEncoding implements Filter{

	//extends와 implements 차이
	//extends는 클래스를 여러개 상속받지 못한다.(인터페이스는 다중상속 가능)
	//implements는 다중 상속 가능
	//단, extends는 사용할 메서드를 선택하여 사용 가능하나, 
	//implements는 속한 메서드는 모두 사용해야 하기 때문에 override로 모든 메서드를 강제로 사용하게 함.
	
	private String encoding;	//인코딩 정보
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {

		System.out.println("현재 인코딩 설정 정보 : " + encoding);
		
		//인코딩 설정하기
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		
		fc.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		
		if(fc.getInitParameter("encoding") == null) {
			this.encoding = "UTF-8";
		}else {
			this.encoding = fc.getInitParameter("encoding");
		}
	}
}

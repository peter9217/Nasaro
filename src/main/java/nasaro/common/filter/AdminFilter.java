package nasaro.common.filter;

import java.io.IOException;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nasaro.member.model.dto.Member;

/**
 * 관리자기능 접근 제한 필터
 */
public class AdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		
		Member member = (Member)session.getAttribute("loginMember");
		
		if(member == null || !member.getMemberRank().equals("A")) {
			resp.sendRedirect("/");
		} else {
			chain.doFilter(request, response);
		}
		
		// 필터 기능 임시 중지
//		chain.doFilter(request, response);
	}

}

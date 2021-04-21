package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class InsertMemberServlet extends HttpServlet{

	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/member/insertForm.jsp");
			
			dispatcher.forward(req, resp);
			
		}
		
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			//1. 요청 파라미터 정보 가져오기
			String memId = req.getParameter("memId");
			String memName = req.getParameter("memName");
			String memTel = req.getParameter("memTel");
			String memAddr = req.getParameter("memAddr");
			
			//2. 서비스 객체 생성하기
			IMemberService memberService = MemberServiceImpl.getInstance();
			
			//3. 회원정보 등록하기
			MemberVO mv = new MemberVO();
			mv.setMemId(memId);
			mv.setMemName(memName);
			mv.setMemAddr(memAddr);
			mv.setMemTel(memTel);
			
			int cnt = memberService.insertMember(mv);
			
			String msg="";
			if(cnt > 0) {
				msg = "성공";
			}else {
				msg="실패";
			}
			
			//4. 목록 조회화면으로 이동
			
			//redirectUrl는 사용자가 요청했을때 location에 바뀐url을 저장해서  쓰인다.
			//redirect는 두번의 요청이 일어난다-->바뀌는 주소를 알려줘서 그쪽으로 간다.
			//일반적으로 회원가입이나 삭제할때 목록으로 가기위해 사용된다.
			String redirectUrl = req.getContextPath()+ //req.getContextPath()가 있어야 가능하다.외부라서,이걸쓰면 url이바뀐다.
					"/member/list.do?msg="+URLEncoder.encode(msg, "UTF-8");
			resp.sendRedirect(redirectUrl);
			
			
			
			//두가지 방법이 있다.
			//화면이 바뀌지않으면 일반적으로 forward를 쓴다.
		/*	req.getRequestDispatcher( //req는 아직 톰캣내부에 있는 객체라 getContextPath가 필요가 없다.이걸로쓰면 url이 바뀌지않는다
					"/member/list.do?msg="
			+ URLEncoder.encode(msg, "UTF-8")).forward(req, resp);*/
			
			
		}
		
	
}













































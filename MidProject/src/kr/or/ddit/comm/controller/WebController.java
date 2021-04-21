package kr.or.ddit.comm.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kr.or.ddit.board.handler.CommandHandler;

public class WebController extends HttpServlet{

	
	private static Logger LOGGER = Logger.getLogger(WebController.class);
	
	//매핑정보 저장(핸들러 객체 저장용 맵)
	private Map<String, CommandHandler> cmmHandlerMap = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {	//init은 초기화하는 영역

		String configFilePath = config.getInitParameter("handler-config");
		
		Properties handlerProp = new Properties();
		
		//설정파일을 읽어서 대응되는 핸들러 객체를 생성하여 맵에 등록하기
		String configFileRealPath = config.getServletContext().getRealPath(configFilePath);
		
		FileReader fr;
		
		try {
			fr = new FileReader(configFileRealPath);
			handlerProp.load(fr);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (Object key : handlerProp.keySet()) {
			String command = (String) key;
			
			try {
				Class<?> klass = Class.forName(handlerProp.getProperty(command));
				CommandHandler handler = (CommandHandler) klass.newInstance();
				
				//핸들러 객체 등록
				cmmHandlerMap.put(command, handler);

			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			}
		}
		
		Set<Map.Entry<String, CommandHandler>> entrySet
			= cmmHandlerMap.entrySet();
		for (Map.Entry<String, CommandHandler> entry : entrySet) {
			LOGGER.info(entry.getKey() + " => " + entry.getValue());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		process(req, resp);
	}

	/**
	 * 요청 처리 메서드
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

		String reqURI = req.getRequestURI();
		
		if(reqURI.indexOf(req.getContextPath()) == 0) {
			//ContextPath 부분을 제외한 URL 정보 가져오기
			reqURI = reqURI.substring(req.getContextPath().length());
		}
		
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info("command : " + reqURI);
			LOGGER.info("cmmHandlerMap : " + cmmHandlerMap); 
		}
		
		CommandHandler handler = cmmHandlerMap.get(reqURI);
		
		if (handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = "";
		
		try {
			
		//핸들러 처리 부분
		viewPage = handler.process(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LOGGER.info("viewPage : " + viewPage);
		
		//VIEW 화면 처리
		if (viewPage != null) {	//뷰페이지가 존재하는 경우
			//리다이렉트 처리가 필요한 경우
			if (handler.isRedirect(req)) {
				resp.sendRedirect(viewPage);
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		}
	}
}

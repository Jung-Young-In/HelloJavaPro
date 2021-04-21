package kr.or.ddit.util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sun.security.pkcs.ParsingException;

public class FileUploadRequestWrapper extends HttpServletRequestWrapper{

	//업로드 경로 설정
	public static final String UPLOAD_DIRECTORY = "d:/D_Other/upload_files"; 
	private boolean multipart = false;	//멀티파트 여부
	
	private Map<String, String[]> parameterMap;	//폼필드(파라미터) 데이터를 담기 위한 맵
	private Map<String, Object> fileItemMap;	//fileItem 객체를 담기 위한 맵
	
	/**
	 * 생성자
	 * @param request
	 * @throws FileNotFoundException
	 */
	public FileUploadRequestWrapper(HttpServletRequest request) throws FileUploadException{
		
		this(request, -1, -1, -1, null);
	}

	public FileUploadRequestWrapper(HttpServletRequest request, int memoryThreshold, long fileSizeMax, long maxRequestSize, String repositoryPath) throws FileUploadException{

		super(request);
		
		parsing(request, memoryThreshold, fileSizeMax, maxRequestSize, repositoryPath);
	}

	/**
	 * 멀티파트 데이터를 파싱하여 두개의 맵에 나누어 담는다.
	 * @param request
	 * @param memoryThreshold 메모리 임계 크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
	 * @param fileSizeMax 파일 1개당 최대 크기
	 * @param maxRequestSize 요청 파일 최대 크기
	 * @param repositoryPath 임시파일 저장경로
	 * @throws FileUploadException
	 */
	private void parsing(HttpServletRequest request, int memoryThreshold, long fileSizeMax, long maxRequestSize,
			String repositoryPath) throws FileUploadException {

		if (ServletFileUpload.isMultipartContent(request)) {
			multipart = true;	//멀티파트임을 설정
			
			parameterMap = new HashMap<>();
			
		}
		
	}

	
}

package kr_or_ddit_member.service;

import java.util.List;

import kr_or_ddit_member.vo.MemberVO;

/**
 *	회원정보 처리를 수행하는 서비스
 */

public interface IMemberService {
	
	/**
	 * MemberVO 회원 등록하는 메서드
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환된다
	 */
	
	public int insertMember(MemberVO mv);
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param memId 회원ID
	 * @return 해당 회원ID가 존재하면 true, 존재하지 않으면 false
	 */
	
	public boolean checkMember(String memId);
	
	/**
	 * 전체 회원 정보 조회 메서드
	 * @return 회원정보를 담고 있는 List객체
	 */
	
	public List<MemberVO> getAllMemberList();
	
	/**
	 * 하나의 회원정보를 수정하는 메서드
	 * @param mv 회원정보 객체
	 * @return 작업성공 : 1, 실패 : 0
	 */
	
	public int updateMember(MemberVO mv);
	
	/**
	 * 회원 정보 삭제 메서드
	 * @param memID 삭제할 회원ID
	 * @return 작업성공 : 1, 실패 : 0
	 */
	
	public int deleteMember(String memID);
}

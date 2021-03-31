package kr_or_ddit_member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr_or_ddit_member.dao.IMemberDao;
import kr_or_ddit_member.dao.MemberDaoImpl;
import kr_or_ddit_member.vo.MemberVO;
import kr_or_ddit_util.JDBCUtil3;

//add unimplemented method로 생성
public class MemberServiceImpl implements IMemberService{

	//사용할 DAO의 객체변수를 선언한다
	private IMemberDao memDao;
	private Connection conn;
	
	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();
	}
	
	
	
	@Override
	public int insertMember(MemberVO mv) {

		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			cnt = memDao.insertMember(conn, mv);
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

		boolean chk = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			chk = memDao.checkMember(conn, memId);
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberList() {

		List<MemberVO> memList = new ArrayList<>();
		
		try {
			conn = JDBCUtil3.getConnection();
			memList = memDao.getAllMemberList(conn);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {

		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			cnt = memDao.updateMember(conn, mv);
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memID) {

		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			cnt = memDao.deleteMember(conn, memID);
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return cnt;
	}

}

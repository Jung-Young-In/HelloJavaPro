package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientUtil;


public class MemberDaoImpl implements IMemberDao {

	private static IMemberDao memDao;
	
	private MemberDaoImpl() { //외부에서 객체생성 방지 메서드 
	}
	
	public static IMemberDao getInstance() { //내부에서는 객체를 만들수있어서 여기서 만들어 준다 ,이미 있다면 만들었던 객체를 리턴해준다
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	
	
	@Override
	public int insertMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		int cnt = 0;
		
		Object obj=smc.insert("member.insertMember", mv);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public boolean checkMember(SqlMapClient smc, String memId) throws SQLException {
		boolean chk = false;
		
		int cnt = (int) smc.queryForObject("member.getMember",memId);
		
		if(cnt > 0) {
			chk =true;
		}
		
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberList(SqlMapClient smc) throws SQLException {
		
		List<MemberVO> memList =smc.queryForList("member.getMemberAll");
	
		return memList;
	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		int cnt = 0;
	
		cnt = smc.update("member.updateMember",mv);
		
		return cnt;
	}

	
	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {
		
		int cnt=0;
		
		cnt = smc.delete("member.deleteMember", memId);
		
		return cnt;
	}

	
	@Override
	public List<MemberVO> getSearchMember(SqlMapClient smc,MemberVO mv) throws SQLException {
		
		List<MemberVO> memList =smc.queryForList("member.getSearchMember",mv);
		
		return memList;
	}
	
	
	
	@Override
	public MemberVO getMember(SqlMapClient smc,String memId) throws SQLException {
		
		MemberVO mv = (MemberVO)smc.queryForObject("member.getMemberInfo",memId);
		
		
		return mv;
	}
}































































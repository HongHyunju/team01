package com.springstudy.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.bbs.domain.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	private final String NAME_SPACE = "com.springstudy.bbs.mapper.BoardMapper";
	
	private SqlSessionTemplate sqlSession;

	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}	
	
	//페이지에 보일 게시글 리스트
	@Override
	public List<Board> boardList(int startRow, int num){
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("startRow", startRow);
		params.put("num", num);
		
		return sqlSession.selectList(NAME_SPACE + ".boardList", params);
	}
	
	@Override
	public int getBoardCount() {
		return sqlSession.selectOne(NAME_SPACE, ".getBoardCount");
	}

	@Override
	public Board getBoard(int no) {		
			 
		return sqlSession.selectOne(NAME_SPACE + ".getBoard", no);
	}

	@Override
	public void insertBoard(Board board) {
		
		sqlSession.insert(NAME_SPACE + ".insertBoard", board);
	}
	
	/* 게시글 수정과 삭제 할 때 비밀번호 체크*/
	public String isPassCheck(int no, String pass) {
		return sqlSession.selectOne(	NAME_SPACE + ".isPassCheck",	no);	
	}
	
	/* 게시글 수정*/
	@Override
	public void updateBoard(Board board) {
		sqlSession.update(NAME_SPACE + ".updateBoard", board);
	}

	/* 게시글 삭제 요청 */
	@Override
	public void deleteBoard(int no) {
		sqlSession.delete(NAME_SPACE + ".deleteBoard", no);
	}
	
}
package com.springstudy.bbs.dao;

import java.util.List;

import com.springstudy.bbs.domain.Board;

public interface BoardDao {
	
	//한 페이지에 보여질 게시글 리스트
	public abstract List<Board> boardList(int startRow, int num);
	
	// DB테이블에 등록된 모든 게시물의 수를 반환하는
	public abstract int getBoardCount();
	
	//게시글 상세보기
	public abstract Board getBoard(int no);
	
	//게시글 쓰기 요청
	public abstract void insertBoard(Board board);
	
	//게시글 수정/삭제 시 비밀번호 체크
	public String isPassCheck(int no, String pass);
	
	//게시글 수정
	public abstract void updateBoard(Board board);
	
	//게시글 삭제
	public abstract void deleteBoard(int no);
}

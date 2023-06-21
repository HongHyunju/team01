package com.springstudy.bbs.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springstudy.bbs.domain.Board;
import com.springstudy.bbs.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	/* 게시글 리스트 보기 요청을 처리하는 메서드*/	
	@RequestMapping(value= {"/boardList", "/list"}, method=RequestMethod.GET)	
								public String boardList(Model model, @RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum) {
		
		// Service 클래스를 이용해 게시글 리스트를 가져온다.
		Map<String, Object> modelMap = boardService.boardList(pageNum);
		model.addAllAttributes(modelMap);
		
		return "boardList";
	}

	/* 게시글 상세보기 요청을 처리하는 메서드*/
	@RequestMapping("/boardDetail")
	public String boardDetail(Model model, int no) {
		
		// Service 클래스를 이용해 no에 해당하는 게시 글을 가져온다.
		Board board = boardService.getBoard(no);
		
		model.addAttribute("board", board);
		
		return "boardDetail";
	}

	/* 게시글 쓰기 폼에서 들어오는 게시글 쓰기 요청을 처리하는 메서드*/
	@RequestMapping(value="/writeProcess", method=RequestMethod.POST)
	public String insertBoard(Board board) {
		
		/* BoardService 클래스를 이용해 폼에서 넘어온 게시글 정보를 게시글 테이블에 추가*/
		boardService.insertBoard(board);	
			
		return "redirect:boardList";
	}
	
	/* 게시글 수정 폼 요청을 처리하는 메서드*/
	@RequestMapping(value="/update")
	public String updateBoard(Model model, HttpServletResponse response, PrintWriter out, int no, String pass,
												@RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum) {
		
		// BoardService 클래스를 이용해 게시판 테이블에서 비밀번호가 맞는지 체크한다. 
		boolean result = boardService.isPassCheck(no, pass);
		
		// 비밀번호가 맞지 않으면
		if(! result) {
			
			response.setContentType("text/html; charset=utf-8");				
			out.println("<script>");
			out.println("	alert('비밀번호가 맞지 않습니다.');");
			out.println("	history.back();");
			out.println("</script>");

			return null;
		}
		
		/* Service 클래스를 이용해 게시글 수정 폼에 출력할 no에 해당하는 게시글을 가져온다. */
		Board board = boardService.getBoard(no);
		model.addAttribute("board", board);
		
		return "updateForm";
	}
	
	/* 게시글 수정 폼에서 들어오는 게시글 수정 요청을 처리하는 메서드*/
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateBoard(HttpServletResponse response, PrintWriter out, Board board, RedirectAttributes reAttrs,
											@RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum) {
	
		// BoardService 클래스를 이용해 게시판 테이블에서 비밀번호가 맞는지 체크한다. 
		boolean result = boardService.isPassCheck(board.getNo(), board.getPass());
		
		// 비밀번호가 맞지 않으면
		if(! result) {

			response.setContentType("text/html; charset=utf-8");				
			out.println("<script>");
			out.println("	alert('비밀번호가 맞지 않습니다.');");
			out.println("	history.back();");
			out.println("</script>");

			return null;
		}
		
		// BoardService 클래스를 이용해 게시판 테이블에서 게시글 수정
		boardService.updateBoard(board);
		
		reAttrs.addAttribute("pageNum", pageNum);
		
		return "redirect:boardList";
	}
	
	/* 게시글 상세보기에서 들어오는 게시글 삭제 요청을 처리하는 메서드*/
	@RequestMapping({"/delete", "deleteBoard"})
	public String deleteBoard(HttpServletResponse response, PrintWriter out, int no, String pass, RedirectAttributes reAttrs,
											@RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum) {
		
		// BoardService 클래스를 이용해 게시판 테이블에서 비밀번호가 맞는지 체크
		boolean result = boardService.isPassCheck(no, pass);
		
		// 비밀번호가 맞지 않으면
		if(! result) {

			response.setContentType("text/html; charset=utf-8");				
			out.println("<script>");
			out.println("	alert('비밀번호가 맞지 않습니다.');");
			out.println("	history.back();");
			out.println("</script>");

			return null;
		}
		
		// BoardService 클래스를 이용해 게시판 테이블에서 게시글 수정
		boardService.deleteBoard(no);
		
		reAttrs.addAttribute("pageNum", pageNum);
		
		return "redirect:boardList";
	}
	
}
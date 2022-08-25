package com.baekhwa.song.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baekhwa.song.domain.dto.board.BoardInsertDTO;
import com.baekhwa.song.domain.dto.board.BoardUpdateDTO;
import com.baekhwa.song.domain.dto.board.ReplyInsertDTO;
import com.baekhwa.song.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService service;
	
	//ajax 요청
	@ResponseBody
	@DeleteMapping("/board/{no}")
	public void update(@PathVariable long no) {
		service.delete(no);
	}
		
	@PutMapping("/board/{no}")
	public String update(@PathVariable long no, BoardUpdateDTO dto) {
		service.update(no, dto);
		return "redirect:/board/"+no;
	}
	
	@GetMapping("/board/{no}")
	public String list(@PathVariable long no, Model model) {
		service.detail(no, model);
		return "board/detail";
	}
	
	//게시글 페이지이동 및 게시글 데이터 읽어오기
	@GetMapping("/board")
	public String list(Model model) {
		service.list(model);
		return "board/list";
	}
	
	//게시글 저장하기
	@PostMapping("/board")
	public String save(BoardInsertDTO dto) {
		service.save(dto);
		return"redirect:/board";
	}
	
	@ResponseBody
	@PostMapping("/board/{no}/reply")
	public boolean reply(ReplyInsertDTO dto) {
		return service.reply(dto);
	}
	
	//상세페이지에서 페이지로딩후 댓글 읽어오기
	
	@GetMapping("/board/{no}/replies")
	public String replies(@PathVariable long bno,Model model) {
		return service.replies(bno, model);
	}
	
	@GetMapping("/auth/board")
	public String write() {
		return "board/write";
	}
}

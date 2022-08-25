package com.baekhwa.song.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.baekhwa.song.domain.dto.board.BoardDetailDTO;
import com.baekhwa.song.domain.dto.board.BoardInsertDTO;
import com.baekhwa.song.domain.dto.board.BoardListDTO;
import com.baekhwa.song.domain.dto.board.BoardUpdateDTO;
import com.baekhwa.song.domain.dto.board.ReplyInsertDTO;
import com.baekhwa.song.domain.dto.board.ReplyListDTO;
import com.baekhwa.song.domain.entity.BoardEntity;
import com.baekhwa.song.domain.entity.BoardEntityRepository;
import com.baekhwa.song.domain.entity.MemberRepository;
import com.baekhwa.song.domain.entity.ReplyEntityRepository;
import com.baekhwa.song.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BoardServiceProc implements BoardService{

	//dao
	private final BoardEntityRepository repository;
	private final MemberRepository mRepository;
	
	@Override
	public void delete(long no) {
		repository.deleteById(no);
	}
	
	//수정처리
	@Transactional
	@Override
	public void update(long no, BoardUpdateDTO dto) {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		log.debug(">>>>> 조회 이후 수정 쿼리가 처리되어야합니다.");
		repository.findById(no).map(e->e.update(dto));
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	@Override
	public void save(BoardInsertDTO dto) {
		//??작성자는 어떻게 하나요? 
		repository.save(dto.toEntity(
				mRepository.findByEmail(dto.getEmail()).orElseThrow()
				));
	}
	
	@Override
	public void list(Model model) {
		model.addAttribute("today", LocalDate.now());
		model.addAttribute("list", repository.findAll().stream()
				.map(BoardListDTO::new)
				.toList());
	}
	
	//상세페이지 구현
	@Transactional
	@Override
	public void detail(long no, Model model) {
		//no - Board의 pk
		Optional<BoardEntity> result=repository.findById(no);
		model.addAttribute("detail", result.map(BoardDetailDTO::new).get());
		result.map(e->e.incrementReadCount());
	}

	@Autowired
	private ReplyEntityRepository replyEntityRepository;
	
	@Override
	public boolean reply(ReplyInsertDTO dto) {
		replyEntityRepository.save(dto.toEntity());
		return true;
	}

	@Override
	public String replies(long bno, Model model) {
		List<ReplyListDTO> result= replyEntityRepository.findAllByBoardEntityNo(bno, Sort.by(Direction.DESC, "rno"))
				.stream().map(ReplyListDTO::new).collect(Collectors.toList());
		model.addAttribute("list", result); //
		return "board/reply/list";
	}
}

package com.baekhwa.song.service;

import org.springframework.ui.Model;

import com.baekhwa.song.domain.dto.board.BoardInsertDTO;
import com.baekhwa.song.domain.dto.board.BoardUpdateDTO;
import com.baekhwa.song.domain.dto.board.ReplyInsertDTO;

public interface BoardService {

	void save(BoardInsertDTO dto);

	void list(Model model);

	void detail(long no, Model model);

	void update(long no, BoardUpdateDTO dto);

	void delete(long no);

	boolean reply(ReplyInsertDTO dto);

	String replies(long bno, Model model);
}

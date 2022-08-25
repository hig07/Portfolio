package com.baekhwa.song.domain.dto.board;

import com.baekhwa.song.domain.entity.BoardEntity;
import com.baekhwa.song.domain.entity.ReplyEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyInsertDTO {

	private long bno;
	private String replier;
	private String text;
	
	public ReplyEntity toEntity() {
		return ReplyEntity.builder()
				.replier(replier).text(text)
				.boardEntity(BoardEntity.builder().no(bno).build())
				.build();
	}
}

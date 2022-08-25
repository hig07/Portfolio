package com.baekhwa.song.domain.dto.board;

import com.baekhwa.song.domain.entity.BoardEntity;
import com.baekhwa.song.domain.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardInsertDTO {
	
	private String title;
	private String content;
	private String email;
	
	public BoardEntity toEntity(Member memberEntity) {
		return BoardEntity.builder()
				.title(title).content(content)
				.member(memberEntity)
				.build();
	}

}

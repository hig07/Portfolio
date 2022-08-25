package com.baekhwa.song.domain.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequestDto {
	
	private String email;
	private String code;

}

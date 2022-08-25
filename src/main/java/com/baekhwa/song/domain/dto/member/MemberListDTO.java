package com.baekhwa.song.domain.dto.member;

import com.baekhwa.song.domain.entity.Member;

import lombok.Getter;

@Getter
public class MemberListDTO {

	private long mno;
	private String email;
	
	public MemberListDTO(Member e) {
		this.mno = e.getMno();
		this.email = e.getEmail();
	}
	
}

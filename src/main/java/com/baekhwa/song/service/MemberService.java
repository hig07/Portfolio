package com.baekhwa.song.service;

import javax.servlet.http.HttpServletRequest;

import com.baekhwa.song.domain.dto.member.MemberInsertDTO;

public interface MemberService {

	String save(MemberInsertDTO dto, HttpServletRequest request);

}

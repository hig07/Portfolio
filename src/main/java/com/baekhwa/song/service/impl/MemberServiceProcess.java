package com.baekhwa.song.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baekhwa.song.domain.dto.member.MemberInsertDTO;
import com.baekhwa.song.domain.entity.MemberRepository;
import com.baekhwa.song.security.MemberRole;
import com.baekhwa.song.service.MemberService;
import com.baekhwa.song.utils.ClientIP;

@Service
public class MemberServiceProcess implements MemberService {

	//DAO : jpa-Repository, mybatis-Mapper, jdbc-Connection(singleton으로 구성)
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	PasswordEncoder pe;
	
	@Override
	public String save(MemberInsertDTO dto, HttpServletRequest request) {
		dto.passEncode(pe);
		//프록시서버(카페24) 또는 로드 밸런스를 통해 서버에 접속한경우 127.0.0.1
		dto.setUserIp(ClientIP.getClientIP(request));
		
		//user롤 적용회원가입
		repository.save(dto.toMember().addRole(MemberRole.USER));
		return "redirect:/signin";
	}

}

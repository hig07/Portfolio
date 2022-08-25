package com.baekhwa.song.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baekhwa.song.domain.dto.member.MemberInsertDTO;
import com.baekhwa.song.service.MailService;
import com.baekhwa.song.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService service;
	private final MailService mailService;
	
	@PostMapping("/common/signup")
	public String signup(MemberInsertDTO dto, HttpServletRequest request) {
		return service.save(dto, request);
	}
	//*
	@ResponseBody
	@PostMapping("/request-key/mail")
	public long requestMailKey(String email) {
		//System.out.println(email);
		return mailService.mailSend(email);
	}
	//*/
	
	@ResponseBody
	@GetMapping("/request-key/getKey")
	public String requestMailKey(HttpSession session) {
		
		System.out.println("마지막 접속시간 :"+session.getLastAccessedTime());
		System.out.println("생성시간 :"+session.getCreationTime());
		System.out.println("유지시간 :" +session.getMaxInactiveInterval());
		//System.out.println(email);
		return (String) session.getAttribute("mailKey");
	}
	
	@ResponseBody
	@GetMapping("/request-key/remove")
	public void requestRemove(HttpSession session) {
		session.removeAttribute("mailKey");
	}

}

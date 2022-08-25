package com.baekhwa.song.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	//로그인 페이지 이동
	@GetMapping("/signin")
	public String login(HttpServletRequest request) {
		String prevPageUrl = request.getHeader("Referer");
		
		System.out.println("로그인이전페이지정보 :" + prevPageUrl);
		
		//다이렉트로 주소입력하고 들어온경우 null:로그인 실패 후 재시도시 or 회원가입시
		if(prevPageUrl != null && !prevPageUrl.contains("/sign")) {
			request.getSession().setAttribute("prevPage", prevPageUrl);
		}
		
		return "sign/signin";
	}
	
	@GetMapping("/loginPage")
	public String loginPage() {
		return "redirect:/";
	}
	
	@GetMapping("/common/signup")
	public String signup() {
		return "sign/signup";
	}

	@GetMapping("/common/booking")
	public String book() {
		return "booking/book";
	}
}

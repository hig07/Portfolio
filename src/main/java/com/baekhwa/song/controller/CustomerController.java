package com.baekhwa.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.baekhwa.song.service.CustomerService;

@Controller// Controller + @ResponseBody
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	//고객센터에 최초 로딩시 : FAQ의 사이트 이용내용만 갖고올꺼에요
	/*
	@GetMapping("/customer/{divName}")
	public String list(@PathVariable String divName, Model model) {
		System.out.println(divName);
		service.list(model, divName);
		
		return "cus/faq/list";
	}
	*/
	
	//ajax로 요청시 리턴 list.html페이지가 response된다. --> result 
	@GetMapping("/customer/{divno}")
	public String list(@PathVariable int divno, Model model) {
		service.list(model, divno);
		return "cus/faq/list";
	}
	
	//단순 페이지 이동
	@GetMapping("/customer")
	public String  customer() {
		return "cus/faq/index";
	}
	
	
	/*
	
	//restController 적용시 사용가능
	@GetMapping("/customer2/{divno}")
	public ModelAndView list(@PathVariable int divno, ModelAndView mv) {
		return service.list(mv, divno);
	}
	
	//페이지이동 위해 ModelAndView를 사용한 case
	@GetMapping("/customer2")
	public ModelAndView customer(ModelAndView mv) {
		mv.setViewName("cus/faq/index");
		return mv;
	}
	*/
}

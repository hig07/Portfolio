package com.baekhwa.song.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baekhwa.song.service.MovieService;

import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	//단순페이지 이동
	@GetMapping("/common/movies")
	public ModelAndView page() {
		return new ModelAndView("movie/page");
	}
	
	@GetMapping("/common/movie-list")
	public ModelAndView dailyBoxOffice(Model model, HttpServletRequest request) throws OpenAPIFault, Exception {
		return service.dailyBoxOffice(model, request);
	}

}

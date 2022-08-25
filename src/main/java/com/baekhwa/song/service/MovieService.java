package com.baekhwa.song.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

public interface MovieService {

	ModelAndView dailyBoxOffice(Model model, HttpServletRequest request) throws OpenAPIFault, Exception;

}

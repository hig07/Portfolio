package com.baekhwa.song.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.baekhwa.song.domain.dto.customer.FaqListDTO;
import com.baekhwa.song.domain.entity.Division;
import com.baekhwa.song.domain.entity.FaqEntityRepository;
import com.baekhwa.song.service.CustomerService;

@Service
public class CustomerServiceProcess implements CustomerService {

	@Autowired
	private FaqEntityRepository repository;
	
	@Override
	public void list(Model model, String div) {
		Division division = null;
		Division[] divs=Division.values();
		
		for(Division di :divs) {
			if(div.equals(di.getUrl())) division=di; 
		}
		
		model.addAttribute("list",   repository.findAllByDivision(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()) );
		
	}

	@Override
	public void list(Model model, int divno) {
		Division division = null;
		Division[] divs=Division.values();
		
		for(Division di :divs) {
			if(divno==di.ordinal())division=di; 
		}
		
		model.addAttribute("list",   repository.findAllByDivision(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()) );
		
	}

	@Override
	public ModelAndView list(ModelAndView mv, int divno) {
		Division division = null;
		Division[] divs=Division.values();
		
		for(Division di :divs) {
			if(divno==di.ordinal())division=di; 
		}
		
		mv.addObject("list",   repository.findAllByDivision(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()) );
		mv.setViewName("/cus/faq/list");
		return mv;
	}

}

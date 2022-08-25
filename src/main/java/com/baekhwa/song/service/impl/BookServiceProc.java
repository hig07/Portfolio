package com.baekhwa.song.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.baekhwa.song.domain.dto.member.MemberListDTO;
import com.baekhwa.song.domain.entity.MemberRepository;
import com.baekhwa.song.service.BookService;

@Service
public class BookServiceProc implements BookService{

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public String indexList(Model model) {
		List<MemberListDTO> result = memberRepository.findAll().stream()
				.map(MemberListDTO::new).collect(Collectors.toList());
		model.addAttribute("list", result);
		
		return "/booking/flightList";
	}

}

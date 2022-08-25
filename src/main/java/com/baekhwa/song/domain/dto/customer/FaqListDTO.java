package com.baekhwa.song.domain.dto.customer;

import com.baekhwa.song.domain.entity.Division;
import com.baekhwa.song.domain.entity.FaqEntity;

import lombok.Getter;

@Getter
public class FaqListDTO {
	
	private long faqNo;
	
	private String question;//질문
	private String answer; //응답E
	
	private Division division;
	
	public FaqListDTO(FaqEntity e){
		faqNo=e.getFaqNo();
		question=e.getQuestion();
		answer=e.getAnswer();
		division=e.getDivision();
	}

}

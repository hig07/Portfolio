package com.baekhwa.song.domain.dto.goods;

import com.baekhwa.song.domain.entity.Category;
import com.baekhwa.song.domain.entity.CategoryA;

import lombok.Getter;

@Getter
public class CategoryDto {
	private long caNo;
	private String name;
	private long code;
	CategoryA catea;
	
	public CategoryDto(Category e) {
		this.caNo = e.getCaNo();
		this.name = e.getName();
		this.code = e.getCode();
		catea=e.getCateA();
	}
	
	
	

}

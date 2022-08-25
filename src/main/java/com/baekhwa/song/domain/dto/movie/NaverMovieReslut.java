package com.baekhwa.song.domain.dto.movie;

import java.util.List;

import lombok.Data;

@Data
public class NaverMovieReslut {
	
	String lastBuildDate;
	int total;
	int start;
	int display;
	List<Item>items;
	

}

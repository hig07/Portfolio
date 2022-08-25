package com.baekhwa.song.domain.dto.movie;

import lombok.Getter;

@Getter
public class MovieListDto {
	//영화진흥위원회 정보
	String rank;
	String movieCd;
	String movieNm;
	String openDt;
	String audiCnt;
	//네이버의 포스터정보
	String link;
	String image;
	
	public MovieListDto(Movie m, Item item) {
		rank=m.getRank();
		movieCd=m.getMovieCd();
		movieNm=m.getMovieNm();
		openDt=m.getOpenDt();
		audiCnt=m.getAudiCnt();
		
		link=item.getLink();
		image=item.getImage();
		
	}
	
	
	
}

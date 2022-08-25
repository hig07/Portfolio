package com.baekhwa.song.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.baekhwa.song.domain.dto.movie.DailyBoxOfficeDto;
import com.baekhwa.song.domain.dto.movie.Movie;
import com.baekhwa.song.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import kr.or.kobis.kobisopenapi.consumer.rest.exception.OpenAPIFault;

@Service
public class MovieServiceProc implements MovieService {
	
	@Value("${movieKey}")
	private String key;
	
	@Override
	public ModelAndView dailyBoxOffice(Model model, HttpServletRequest request) throws OpenAPIFault, Exception{
		//어제
		LocalDate yesterday = LocalDate.now().minusDays(1);
		request.setAttribute("basic", yesterday);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		//영화진흥위원회에서 필수로 입력해야하는 날자 패턴
		//파라미터 설정
		String targetDt = request.getParameter("targetDt")==null?yesterday.format(formatter):request.getParameter("targetDt");  //조회일자
		String itemPerPage = request.getParameter("itemPerPage")==null?"10":request.getParameter("itemPerPage");  //결과row수
		String multiMovieYn = request.getParameter("multiMovieYn")==null?"":request.getParameter("multiMovieYn");  //“Y” : 다양성 영화 “N” : 상업영화 (default : 전체)
		String repNationCd = request.getParameter("repNationCd")==null?"":request.getParameter("repNationCd");  //“K: : 한국영화 “F” : 외국영화 (default : 전체)
		String wideAreaCd = request.getParameter("wideAreaCd")==null?"":request.getParameter("wideAreaCd");  //“0105000000” 로서 조회된 지역코드

		//KOBIS 오픈 API Rest Client를 통해 호출
	    KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);

		//일일 박스오피스 서비스 호출 (boolean isJson, String targetDt, String itemPerPage,String multiMovieYn, String repNationCd, String wideAreaCd)
	    String dailyResponse = service.getDailyBoxOffice(true,targetDt,itemPerPage,multiMovieYn,repNationCd,wideAreaCd);

		//Json 라이브러리를 통해 Handling
		ObjectMapper mapper = new ObjectMapper();
		DailyBoxOfficeDto dailyResult = mapper.readValue(dailyResponse, DailyBoxOfficeDto.class);

		List<Movie> result = dailyResult.getBoxOfficeResult().getDailyBoxOfficeList();
		
		ModelAndView mv=new ModelAndView("movie/list");
		mv.addObject("list", result);
		
		return mv;
	}

}

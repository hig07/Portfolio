package com.baekhwa.song.domain.dto.bus;

import lombok.Data;

@Data
public class BusItem {

	private String busRouteId;//노선ID
	private String busRouteNm;//노선명
	private String busRouteAbrv;
	private String length; //노선길이
	private String routeType; //노선유형
	private String stStationNm; //기점
	private String edStationNm;//종점
	private String term;//배차간격
	private String lastBusYn;//막차운행여부
	private String lastBusTm;//금일막차시간
	private String firstBusTm;//금일첫차시간
	private String lastLowTm;//저상버스첫차시간
	private String firstLowTm;//저상버스막차시간
	private String corpNm; //운송사명
}

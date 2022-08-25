package com.baekhwa.song.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryA {
	
	SHOES("신발",1100,"shoes"),
	BLANKET("이불",1200,"blanket"),
	PILLOW("베게",1300,"pillow"),
	MAT("요",1400,"mat"),
	COVER("커버",1500,"cover"),
	HOTELSET("호텔식 세트",1600,"hotelset"),
	REFUR("리퍼 상품",1700,"refur"),
	GOOSE("구스 함량별",1800,"goose"),
	SEASON("계절별",1900,"season"),
	EXHIBITION("기획전",2000,"exhibition");
	
	final String koName;
	final long code;
	final String lower;

}

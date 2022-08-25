package com.baekhwa.song.domain.dto.goods;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrderDto {

	@JsonProperty
	private long gno;
	
	@JsonProperty
	private int ea;
}

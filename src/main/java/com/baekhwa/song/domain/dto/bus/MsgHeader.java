package com.baekhwa.song.domain.dto.bus;

import lombok.Data;

@Data
public class MsgHeader {

	private String headerMsg;
	private String headerCd;
	private int  itemCount;
}

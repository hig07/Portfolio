package com.baekhwa.song.domain.dto.bus;

import lombok.Data;

@Data
public class ComMsgHeader {
	
	private String  requestMsgID;
	private String  responseMsgID;
	private String  responseTime;
	private String  errMsg;
	private String  successYN;
	private String  returnCode;
}

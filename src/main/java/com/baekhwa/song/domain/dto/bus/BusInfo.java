package com.baekhwa.song.domain.dto.bus;

import lombok.Data;

@Data
public class BusInfo {
	ComMsgHeader comMsgHeader;
	MsgHeader msgHeader;
	MsgBody msgBody;
}

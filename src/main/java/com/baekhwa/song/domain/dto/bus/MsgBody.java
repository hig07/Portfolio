package com.baekhwa.song.domain.dto.bus;

import java.util.List;

import lombok.Data;

@Data
public class MsgBody {

	List<BusItem> itemList;
}

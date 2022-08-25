package com.baekhwa.song.domain.dto.goods;

import com.baekhwa.song.domain.entity.GoodsEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class GoodsInsertDTO {
	
	private String name;
	private int price;
	private int sale;
	private boolean rate;//false:원, true:%
	private int stock;
	private String defImgName;
	private String addImgName;
	private String content;
	
	private long[] caNo;
	
	public GoodsEntity toEntity() {
		if(rate) {
			sale=price*sale/100;
		}
		return GoodsEntity.builder()
				.name(name).price(price).sale(sale).stock(stock).content(content)
				.build();
	}
}

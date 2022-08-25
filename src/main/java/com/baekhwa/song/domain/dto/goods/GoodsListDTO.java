package com.baekhwa.song.domain.dto.goods;

import com.baekhwa.song.domain.entity.GoodsEntity;

import lombok.Getter;

@Getter
public class GoodsListDTO {
	private long gno;
	private String name;
	private int price;//판매가,정가
	private int sale;//할인가 or 할인율
	private int stock;//재고
	private int eaPrice; 
	
	private String defImgUrl;

	public GoodsListDTO(GoodsEntity e) {
		this.gno = e.getGno();
		this.name = e.getName();
		this.price = e.getPrice();
		this.sale = e.getSale();
		this.stock = e.getStock();
		eaPrice = price - sale;
		e.getFiles().forEach(fe->{
			if(fe.isDefImg())defImgUrl=fe.getUrl()+fe.getOrgName();
		});
	}
	
}

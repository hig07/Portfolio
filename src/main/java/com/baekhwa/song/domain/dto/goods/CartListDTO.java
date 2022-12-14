package com.baekhwa.song.domain.dto.goods;

import com.baekhwa.song.domain.entity.Cart;

import lombok.Getter;

@Getter
public class CartListDTO {

	private long no; //์นดํธ pk
	private int ea; //์๋
	
	private GoodsListDTO goods;

	public CartListDTO(Cart e) {
		this.no = e.getNo();
		this.ea = e.getEa();
		this.goods = new GoodsListDTO(e.getGoods());
	}
	
}

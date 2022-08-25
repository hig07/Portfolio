package com.baekhwa.song.domain.dto.goods;

import com.baekhwa.song.domain.entity.Cart;

import lombok.Getter;

@Getter
public class CartListDTO {

	private long no; //카트 pk
	private int ea; //수량
	
	private GoodsListDTO goods;

	public CartListDTO(Cart e) {
		this.no = e.getNo();
		this.ea = e.getEa();
		this.goods = new GoodsListDTO(e.getGoods());
	}
	
}

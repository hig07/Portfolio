package com.baekhwa.song.domain.dto.goods;

import com.baekhwa.song.domain.entity.Cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartInsertDto {
	
	private long gno;
	private int ea;
	private String email;
	
	public CartInsertDto email(String email) {
		this.email=email;
		return this;
	}

	public Cart toEntity() {
		
		return Cart.builder()
				.ea(ea)
				.build();
	}

}

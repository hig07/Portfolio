package com.baekhwa.song.domain.dto.goods;

import com.baekhwa.song.domain.entity.GoodsFile;

import lombok.Getter;

@Getter
public class GoodsFileDTO {
	
	private long no;
	private String url;
	private String orgName;
	private String newName;
	private boolean isDefImg;
	private long size;
	
	public GoodsFileDTO(GoodsFile e) {
		this.no = e.getNo();
		this.url = e.getUrl();
		this.orgName = e.getOrgName();
		this.newName = e.getNewName();
		this.isDefImg = e.isDefImg();
		this.size = e.getSize();
	}

}

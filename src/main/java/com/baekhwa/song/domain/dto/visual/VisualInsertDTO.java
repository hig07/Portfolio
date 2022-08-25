package com.baekhwa.song.domain.dto.visual;

import com.baekhwa.song.domain.dto.FileData;
import com.baekhwa.song.domain.entity.VisualFile;

import lombok.Setter;

@Setter
public class VisualInsertDTO extends FileData{
	
	private String title;
	private String sub;
	private String link;
	
	public VisualFile toVisualFile() {
		return VisualFile.builder()
				.title(title).sub(sub).link(link)//.num(num)
				.url(url).orgName(orgName).size(size)
				.build();
	}

	public void addFileData(FileData fileData) {
		url=fileData.getUrl();
		orgName=fileData.getOrgName();
		size=fileData.getSize();
	}

}

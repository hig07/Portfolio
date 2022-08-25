package com.baekhwa.song.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.baekhwa.song.domain.dto.visual.VisualInsertDTO;
import com.baekhwa.song.domain.dto.visual.VisualUpdateDto;

public interface VisualService {

	String save(MultipartFile vimg, VisualInsertDTO dto);

	String list(Model model);

	String indexList(Model model);

	boolean updateIsShow(long vno, boolean isShow);

	boolean updateTitle(long vno, String title);

	boolean updateSub(long vno, String sub);

	boolean updateData(long vno, VisualUpdateDto dto);

	boolean delete(long vno);


}

package com.baekhwa.song.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.baekhwa.song.domain.dto.goods.CartInsertDto;
import com.baekhwa.song.domain.dto.goods.CategoryDto;
import com.baekhwa.song.domain.dto.goods.GoodsInsertDTO;

public interface GoodsService {

	String tempFileupload(MultipartFile file);

	String save(GoodsInsertDTO dto);

	String list(Model model);

	String indexList(Model model);

	String detail(long gno, Model model);

	List<CategoryDto> categoryList(long caNo);

	void goodsListByCategory(long caNo, Model model);

	void goodsListByCaNo(long caNo, Model model);

	String saveCart(CartInsertDto dto);

	void getCarts(String email, Model model);

	void updateCartEa(long gno, int ea);

}

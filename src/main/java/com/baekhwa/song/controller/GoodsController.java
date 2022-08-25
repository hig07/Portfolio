package com.baekhwa.song.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baekhwa.song.domain.dto.goods.CartInsertDto;
import com.baekhwa.song.domain.dto.goods.GoodsInsertDTO;
import com.baekhwa.song.domain.dto.goods.OrderDto;
import com.baekhwa.song.domain.entity.CategoryA;
import com.baekhwa.song.service.GoodsService;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService service;
	
	@ResponseBody
	@PostMapping("/member/order")
	public void order(OrderDto[] orderDto) {
		for(OrderDto dto:orderDto) {
			System.out.println(dto);
		}
	}
	
	//카드 구매개수 수정
	@ResponseBody
	@PutMapping("/member/carts/{gno}")
	public void cart(@PathVariable long gno, int ea) {
		service.updateCartEa(gno, ea);
	}
	
	@GetMapping("/member/carts")
	public String cart(Model model, Principal principal) {
		service.getCarts(principal.getName(), model);
		return "member/cart";
	}
	
	@ResponseBody
	@PostMapping("/goods/carts")
	public String carts(CartInsertDto dto, Principal principal) {
		//System.out.println("gno:"+gno);
		//System.out.println("ea"+ea);
		//System.out.println("principal: "+principal.getName());
		
		return service.saveCart(dto.email(principal.getName()));
	}
	
	@GetMapping("/common/categorys/{caNo}/goods")
	public String goodsListByCategory(@PathVariable long caNo, Model model) {
		service.goodsListByCaNo(caNo, model);
		return "goods/list";
	}
	
	//ajax로 요청
	@GetMapping("/admin/category/{caNo}")
	public String category(@PathVariable long caNo, Model model) {
		model.addAttribute("option", service.categoryList(caNo));
		return "admin/goods/category-data";
	}
	
	//상품등록페이지이동
	@GetMapping("/admin/goods/write")
	public String goods(Model model) {
		model.addAttribute("cateA", CategoryA.values());
		for(CategoryA cate : CategoryA.values()) {
			System.out.println(cate.getKoName());
		}
		return "admin/goods/write";
	}
	
	@ResponseBody
	@PostMapping("/admin/goods/fileupload")
	public String tempFileupload(MultipartFile file) {
		
		return service.tempFileupload(file);
	}
	
	@GetMapping("/admin/goods")
	public String list(Model model) {
		return service.list(model);
	}
	
	@GetMapping("/common/goods")
	public String indexList(Model model) {
		return service.indexList(model);
	}
	
	@GetMapping(path =  "/common/goods/{gno}")
	public String detail(@PathVariable long gno ,Model model) {
		return service.detail(gno, model);
	}
	
	@PostMapping("/admin/goods")
	public String goods(GoodsInsertDTO dto) {
		System.out.println(dto);
		return service.save(dto);
	}
}

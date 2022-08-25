package com.baekhwa.song.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.baekhwa.song.domain.dto.goods.CartInsertDto;
import com.baekhwa.song.domain.dto.goods.CategoryDto;
import com.baekhwa.song.domain.dto.goods.GoodsDetailDTO;
import com.baekhwa.song.domain.dto.goods.GoodsInsertDTO;
import com.baekhwa.song.domain.dto.goods.GoodsListDTO;
import com.baekhwa.song.domain.entity.Cart;
import com.baekhwa.song.domain.entity.CartRepository;
import com.baekhwa.song.domain.entity.CategoryA;
import com.baekhwa.song.domain.entity.CategoryRepository;
import com.baekhwa.song.domain.entity.GoodsEntity;
import com.baekhwa.song.domain.entity.GoodsEntityRepository;
import com.baekhwa.song.domain.entity.GoodsFile;
import com.baekhwa.song.domain.entity.MemberRepository;
import com.baekhwa.song.service.GoodsService;

@Service
public class GoodsServiceProc implements GoodsService {

	@Autowired
	private GoodsEntityRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	//회원의 카트 정보 읽어오기
	@Override
	public void getCarts(String email, Model model) {
		model.addAttribute("list", cartRepository.findAllByMemberEmail(email));
	}
	
	@Transactional
	@Override
	public void updateCartEa(long gno, int ea) {
		cartRepository.findByGoodsGno(gno).map(cart->cart.updateEa(ea));
	}

	//카트에 저장
	@Override
	public String saveCart(CartInsertDto dto) {
		String str="save";
		Optional<Cart> result =cartRepository.findByGoodsGno(dto.getGno());
		if(result.isEmpty()) {
			cartRepository.save(dto.toEntity()
					.goods(GoodsEntity.builder().gno(dto.getGno()).build())
					.member(memberRepository.findByEmail(dto.getEmail()).orElseThrow())
					);
		}else {
			Cart entity=result.get().plusEa(dto.getEa());
			cartRepository.save(entity);
			str="update";
		}
		//if(entity==null)return false;//카드 저장 실패
		return str;//카트 저장 성공
	}

	@Override
	public void goodsListByCaNo(long caNo, Model model) {
		
		//상위 카테고리인지 하위카테고리인지
		if(caNo%100==0) {//상위카테고리 이면
			model.addAttribute("list", repository.findAllByCategorysCaNoBetween(caNo, caNo+99).stream()
					.map(GoodsListDTO::new)
					.toList());
			for(CategoryA catea:CategoryA.values()) {
				if(catea.getCode()==caNo) {
					model.addAttribute("catea", catea);
				}
			}
			return;//메서드 종료 //아래문장 실행하지 않아요
		}
		// 하위카테고리인경우
		CategoryDto cateInfo=categoryRepository.findById(caNo).map(CategoryDto::new).get();
		model.addAttribute("cateInfo", cateInfo);
		
		model.addAttribute("list", 
				repository.findAllByCategorysCaNo(caNo).stream()
				.map(GoodsListDTO::new)
				.toList());
	}

	//카테고리별 상품목록
	@Override
	public void goodsListByCategory(long caNo, Model model) {
		for(CategoryA catea:CategoryA.values()) {
			if(catea.getCode()==caNo) {
				model.addAttribute("catea", catea);
			}
		}
		model.addAttribute("list", repository.findAllByCategorysCaNoBetween(caNo, caNo+99).stream()
								.map(GoodsListDTO::new).toList());
	}
	
	@Override
	public String tempFileupload(MultipartFile file) {
		
		String path="/images/goods/temp/";
		ClassPathResource cpr=new ClassPathResource("static"+path);
		
		try {
			File location=cpr.getFile();
			File targetFile=new File(location, file.getOriginalFilename());
			file.transferTo(targetFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path+file.getOriginalFilename();//   /images/goods/temp/파일이름.jpg
	}

	@Override
	public String save(GoodsInsertDTO dto) {
		System.out.println(dto);
		//서버에
		String def=dto.getDefImgName();
		String add=dto.getAddImgName();
		String path="/images/goods/temp/";
		ClassPathResource cpr=new ClassPathResource("static"+path);
		
		GoodsEntity entity=dto.toEntity();
		
		try {
			File root=cpr.getFile();
			//ClassPathResource target=new ClassPathResource("static"+"/images/goods/");
			///////////////////////////////////////////////////////////
			File defFile=new File(root, def);
			defFile.renameTo(new File(root.getParent(), def));
			String name=defFile.getName();
			long size=defFile.length();
			
			GoodsFile defGoodsFile=GoodsFile.builder()
			.newName(name).orgName(name).size(size).isDefImg(true).url("/images/goods/")
			.build();
			/////////////////////////////////////////////////////////
			File addFile=new File(root, add);
			addFile.renameTo(new File(root.getParent(), add));
			name=addFile.getName();
			size=addFile.length();
			GoodsFile addGoodsFile=GoodsFile.builder()
			.newName(name).orgName(name).size(size).url("/images/goods/")
			.build();
			
			//파일 추가
			entity
			.addFile(defGoodsFile)
			.addFile(addGoodsFile);
			
			
			//다중 카테고리 추가
			for(long cano : dto.getCaNo()) {
				entity.addCategory(categoryRepository.findById(cano).get());
			}
			
			repository.save(entity);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:goods";
	}

	@Override
	public String list(Model model) {
		//List<GoodsEntity> -> List<GoodsListDTO>
		List<GoodsListDTO> result=repository.findAll().stream()
				.map(GoodsListDTO::new)
				.collect(Collectors.toList());
		
		model.addAttribute("list", result);
		
		return "admin/goods/list";
	}

	@Override
	public String indexList(Model model) {
		//List<GoodsEntity> -> List<GoodsListDTO>
		List<GoodsListDTO> result=repository.findAll().stream()
				.map(GoodsListDTO::new)
				.collect(Collectors.toList());
		
		model.addAttribute("list", result);
		return "admin/goods/list-data";
	}

	@Override
	public String detail(long gno, Model model) {
		model.addAttribute(
				"detail", 
				repository.findById(gno).map(GoodsDetailDTO::new).get());
		
		return "goods/detail";
	}
	
	
	@Override
	public List<CategoryDto> categoryList(long caNo) {
		//1100 --> 1101~1199
		
		
		/*
		for(CategoryA catea : CategoryA.values()) {
			if(catea.getCode()==caNo) {
				categoryRepository.findByCateA(catea);
			}
		}
		*/
		
		return categoryRepository.findByCaNoBetween(caNo, caNo+99).stream()
				.map(CategoryDto::new)
				.toList();
	}

}

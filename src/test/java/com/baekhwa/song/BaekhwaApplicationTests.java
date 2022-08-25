package com.baekhwa.song;

import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import com.baekhwa.song.domain.entity.Category;
import com.baekhwa.song.domain.entity.CategoryA;
import com.baekhwa.song.domain.entity.CategoryRepository;
import com.baekhwa.song.domain.entity.Division;
import com.baekhwa.song.domain.entity.FaqEntity;
import com.baekhwa.song.domain.entity.FaqEntityRepository;
import com.baekhwa.song.domain.entity.GoodsEntity;
import com.baekhwa.song.domain.entity.GoodsEntityRepository;
import com.baekhwa.song.domain.entity.Member;
import com.baekhwa.song.domain.entity.MemberRepository;
import com.baekhwa.song.security.MemberRole;

@SpringBootTest
class BaekhwaApplicationTests {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	FaqEntityRepository faqEntityRepository;
	
	@Autowired
	PasswordEncoder pe;
	
	@Autowired
	CategoryRepository caRepository;
	
	@Autowired
	GoodsEntityRepository goodsRepository;
	
	@Commit
	@Transactional
	@Test
	void 카테고리_연결() {
		GoodsEntity entity=goodsRepository.findById(2L).get();
		Category category=caRepository.findById(1101L).get();
		goodsRepository.save(entity.addCategory(category));
	}
	
	//@Test
	void 카테고리_생성() {
		CategoryA cateA=CategoryA.SHOES;
		long count=caRepository.countByCateA(cateA);
		
		//System.out.println("count : "+count);
		
		caRepository.save(Category.builder()
				.name("운동화")
				.code(++count)
				.cateA(cateA)
				.build().createCaNo());
	}
	
	//@Test
	void 테스트데이터(){
		IntStream.rangeClosed(1, 10).forEach(i->{
			Division[] disv=Division.values();
			for(int x=0; x<disv.length; x++) {		
			
			faqEntityRepository.save(FaqEntity.builder()
					.question(disv[x].name()+ "질문"+i).answer(disv[x].name()+"답변"+i).division(disv[x])
					.build());
			}
		});
	}
	
	//@Test
	void 관리자생성() {
		memberRepository.save(Member.builder()
				.email("admin").pass(pe.encode("1111")).name("관리자").userIp("127.0.0.1")
				.build().addRole(MemberRole.ADMIN).addRole(MemberRole.USER));
	}
	
	//@Test
	void 회원생성() {
		memberRepository.save(Member.builder()
				.email("test02@test.com").pass(pe.encode("1111")).name("테스트02").userIp("127.0.0.1")
				.build().addRole(MemberRole.USER));
	}
	
	//insert into member 
	//(created_date, updated_date, email, is_deleted, name, pass, phone, user_ip)
	// values (?, ?, ?, ?, ?, ?, ?, ?)
	//insert into member 
	//(created_date, updated_date, email, is_deleted, name, pass, user_ip) 
	// values (?, ?, ?, ?, ?, ?, ?)
	@Rollback(value =false )
	@Transactional
	//@Test
	void 회원수정테스트() {
		System.out.println("-------------------");
		//Member entity2=entity.pass("2222");
		//Member entity3=entity2.name("이름변경");
		//Hibernate: update member set created_date=?, deleted_date=?, email=?, is_deleted=?, name=?, pass=?, updated_date=?, user_ip=? where mno=?
		//update member set name=?, pass=?, updated_date=? where mno=?
		//memberRepository.save(entity);
	}

}

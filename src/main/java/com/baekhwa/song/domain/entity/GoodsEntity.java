package com.baekhwa.song.domain.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class GoodsEntity extends BaseTimeEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long gno;
	
	private String name;
	private int price; //판매가,정가
	private int sale; //할인가 or 할인율
	private int stock; //재고
	
	@Column(columnDefinition = "text", nullable = false)
	private String content;
	
	//색상
	@Builder.Default
	@Enumerated(EnumType.STRING)
	@ElementCollection //@CollectionTable(name = "colors")
	private Set<Color> colors=new HashSet<>();
	
	//사이즈
	@Builder.Default
	@Enumerated(EnumType.STRING)
	@ElementCollection //@CollectionTable(name = "size")
	private Set<Size> size=new HashSet<>();

	//1:N 단방향설정 연관테이블 생성시키지 않기위해 @JoinColumn 해주어야한다
	@Builder.Default
	@JoinColumn(name = "gno")// fk 이름 설정 다쪽(N) entity에 생성 (files_gno)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<GoodsFile> files=new ArrayList<>();
	
	public GoodsEntity addFile(GoodsFile file) {
		files.add(file);
		return this;
	}
	
	@Builder.Default	
	@JoinColumn //categorys_ca_no
	@ManyToMany(cascade = CascadeType.ALL)
	Set<Category> categorys=new HashSet<>();
	
	public GoodsEntity addCategory(Category category) {
		categorys.add(category);
		return this;
	}
}

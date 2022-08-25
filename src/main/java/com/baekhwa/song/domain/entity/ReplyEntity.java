package com.baekhwa.song.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
public class ReplyEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long rno;
	
	@Column(nullable = false)
	private String text;
	
	@Column(nullable = false)
	private String replier;
	
	@CreationTimestamp 
	private LocalDateTime createdDate;
	
	@UpdateTimestamp 
	private LocalDateTime updatedDate;
	
	//주 엔티티
	@JoinColumn(name = "bno") //FK이름으로 만들어진다. //name 안하면 BoardEntity_no: 필드명_pk
	@ManyToOne
	private BoardEntity boardEntity; //게시글의 pk---fk
	//다대1
}

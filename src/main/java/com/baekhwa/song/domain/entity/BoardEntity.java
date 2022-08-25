package com.baekhwa.song.domain.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.baekhwa.song.domain.dto.board.BoardUpdateDTO;

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
public class BoardEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;
	
	@Column(nullable = false)
	private String title;

	@Column(nullable = false, columnDefinition = "text")
	private String content;
	
	private int readCount;
	
	@Column(columnDefinition = "timestamp")
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(columnDefinition = "timestamp")
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	//N:1
	@JoinColumn(name = "mno") //fk
	@ManyToOne(cascade = CascadeType.DETACH) //항상 Entity에 영향, 읽기옵션
	private Member member;

	//수정
	public BoardEntity update(BoardUpdateDTO dto) {
		title = dto.getTitle();
		content = dto.getContent();
		return this;
	}

	public BoardEntity incrementReadCount() {
		readCount++;
		return this;
	}
	
	@Builder.Default
	@JoinColumn(name = "bno")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<ReplyEntity> replies = new Vector<ReplyEntity>();
	
	public BoardEntity addReply(ReplyEntity e) {
		replies.add(e);
		return this;
	}
}

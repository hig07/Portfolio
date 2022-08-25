package com.baekhwa.song.domain.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyEntityRepository extends JpaRepository<ReplyEntity, Long>{

	List<ReplyEntity> findAllByBoardEntityNo(long bno);
	List<ReplyEntity> findAllByBoardEntityNoOrderByRnoDesc(long bno);
	List<ReplyEntity> findAllByBoardEntityNo(long bno, Sort sort);
	
	Page<ReplyEntity> findAllByBoardEntityNo(long bno, Pageable pageable);
}

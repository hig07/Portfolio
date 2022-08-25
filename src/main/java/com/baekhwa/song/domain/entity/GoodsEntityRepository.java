package com.baekhwa.song.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsEntityRepository extends JpaRepository<GoodsEntity, Long>{

	List<GoodsEntity> findAllByCategorysCaNo(long caNo);

	List<GoodsEntity> findAllByCategorysCaNoBetween(long caNo, long l);

}

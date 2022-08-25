package com.baekhwa.song.domain.dto.movie;

import lombok.Data;

@Data
public class Item {
	String title;
	String link;
	String image;
	String subtitle;
	String pubDate;
	String director;
	String actor;
	String userRating;
}

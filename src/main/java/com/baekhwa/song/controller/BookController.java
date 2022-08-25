package com.baekhwa.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.baekhwa.song.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/book/flightList")
	public String indexList(Model model) {
		return bookService.indexList(model);
	}
	
}

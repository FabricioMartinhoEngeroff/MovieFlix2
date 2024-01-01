package com.devFabricio.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devFabricio.movieflix.dto.GenreDTO;
import com.devFabricio.movieflix.services.GenerService;

@RestController
@RequestMapping(value = "/genres")
public class GenerController {

	@Autowired
	private GenerService genreService;

	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAllGenres() {
		List<GenreDTO> list = genreService.findAllGenres();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<GenreDTO> findById(@PathVariable Long id) {
		GenreDTO dto = genreService.findById(id);
		return ResponseEntity.ok().body(dto);
	}

}

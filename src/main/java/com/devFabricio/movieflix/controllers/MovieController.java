package com.devFabricio.movieflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devFabricio.movieflix.dto.MovieDTO;
import com.devFabricio.movieflix.dto.ReviewDTO;
import com.devFabricio.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findAllMovies(Pageable pageable) {
		Page<MovieDTO> list = movieService.findAllMovies(pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		MovieDTO dto = movieService.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/genre/{genre}")
	public ResponseEntity<Page<MovieDTO>> findMoviesByGenre(@PathVariable String genre, Pageable pageable) {
		Page<MovieDTO> list = movieService.findMoviesByGenre(genre, pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{movieId}/reviews")
	public ResponseEntity<Page<ReviewDTO>> findReviewsByMovieId(@PathVariable Long movieId, Pageable pageable) {
		Page<ReviewDTO> list = movieService.findReviewsByMovieId(movieId, pageable);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping(value = "/{movieId}/reviews")
	public ResponseEntity<ReviewDTO> addReview(@PathVariable Long movieId, @RequestBody String text) {
		ReviewDTO dto = movieService.addReview(movieId, text);
		return ResponseEntity.ok().body(dto);
	}
}

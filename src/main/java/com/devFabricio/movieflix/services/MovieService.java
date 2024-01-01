package com.devFabricio.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devFabricio.movieflix.dto.MovieDTO;
import com.devFabricio.movieflix.dto.ReviewDTO;
import com.devFabricio.movieflix.entities.Movie;
import com.devFabricio.movieflix.entities.Review;
import com.devFabricio.movieflix.entities.User;
import com.devFabricio.movieflix.repositories.MovieRepository;
import com.devFabricio.movieflix.repositories.ReviewRepository;
import com.devFabricio.movieflix.services.exceptions.ResourceNotFoundExceptions;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private AuthService authService;

	@Transactional(readOnly = true)
	public Page<MovieDTO> findAllMovies(Pageable pageable) {
		Page<Movie> movies = movieRepository.findAll(pageable);
		return movies.map(MovieDTO::new);
	}

	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = movieRepository.findById(id);
		return obj.map(MovieDTO::new).orElseThrow(() -> new ResourceNotFoundExceptions("Filme não encontrado"));
	}

	@Transactional(readOnly = true)
	public Page<MovieDTO> findMoviesByGenre(String genre, Pageable pageable) {
		Page<Movie> movies = movieRepository.findMovieByGenre(genre, pageable);
		return movies.map(MovieDTO::new);
	}

	@Transactional(readOnly = true)
	public Page<ReviewDTO> findReviewsByMovieId(Long movieId, Pageable pageable) {
		Page<Review> reviews = reviewRepository.findByMovieId(movieId, pageable);
		return reviews.map(ReviewDTO::new);
	}

	@Transactional
	public ReviewDTO addReview(Long movieId, String text) {
		Movie movie = movieRepository.findById(movieId).orElseThrow();
		User user = authService.authenticated();
		Review review = new Review(null, text, user, movie);
		Review savedReview = reviewRepository.save(review);
		return new ReviewDTO(savedReview);
	}
}

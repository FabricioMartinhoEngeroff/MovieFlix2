package com.devFabricio.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devFabricio.movieflix.dto.ReviewDTO;
import com.devFabricio.movieflix.entities.Movie;
import com.devFabricio.movieflix.entities.Review;
import com.devFabricio.movieflix.entities.User;
import com.devFabricio.movieflix.repositories.MovieRepository;
import com.devFabricio.movieflix.repositories.ReviewRepository;
import com.devFabricio.movieflix.repositories.UserRepository;
import com.devFabricio.movieflix.services.exceptions.ResourceNotFoundExceptions;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public Page<ReviewDTO> findAllReviews(Pageable pageable) {
		Page<Review> reviews = reviewRepository.findAll(pageable);
		return reviews.map(ReviewDTO::new);
	}

	@Transactional(readOnly = true)
	public ReviewDTO insert(ReviewDTO dto) {
		Review review = new Review();
		review.setText(dto.getText());

		Movie movie = movieRepository.findById(dto.getMovieId())
				.orElseThrow(() -> new ResourceNotFoundExceptions("Filme não encontrado"));
		review.setMovie(movie);

		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundExceptions("Usuário não encontrado"));
		review.setUser(user);

		review = reviewRepository.save(review);
		return new ReviewDTO(review);
	}

}

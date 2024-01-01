package com.devFabricio.movieflix.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.devFabricio.movieflix.entities.Review;

public class ReviewDTO {

	private Long id;

	@NotBlank(message = "Text is required")
	@Size(max = 500, message = "The text must have a maximum of 500 characters")
	private String text;

	@NotNull(message = "Movie ID is required")
	private Long movieId;

	@NotNull(message = "User ID is required")
	private Long userId;

	public ReviewDTO() {
	}

	public ReviewDTO(Long id, String text, Long movieId, Long user, Long userId) {
		super();
		this.id = id;
		this.text = text;
		this.movieId = movieId;
		this.userId = userId;
	}

	public ReviewDTO(Review entity) {
		id = entity.getId();
		text = entity.getText();
		movieId = entity.getMovie().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}

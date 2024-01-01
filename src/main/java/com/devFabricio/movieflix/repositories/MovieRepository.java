package com.devFabricio.movieflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devFabricio.movieflix.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT m FROM Movie m JOIN m.genre g " +
	           "WHERE (:genreId IS NULL OR g.id = :genreId) " +
	           "ORDER BY m.title")
	    Page<Movie> findMovieByGenre(@Param("genreId") String genre, Pageable pageable);
	
}

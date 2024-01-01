package com.devFabricio.movieflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devFabricio.movieflix.entities.Genre;
import com.devFabricio.movieflix.entities.Movie;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

	 @Query("SELECT m FROM Movie m JOIN m.genre g " +
	           "WHERE (:genreId IS NULL OR g.id = :genreId) " +
	           "ORDER BY m.title")
	    List<Movie> findAllMoviesByGenreId(@Param("genreId") String genre);
	
}

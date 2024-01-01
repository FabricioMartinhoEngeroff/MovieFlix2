package com.devFabricio.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devFabricio.movieflix.dto.GenreDTO;
import com.devFabricio.movieflix.entities.Genre;
import com.devFabricio.movieflix.repositories.GenreRepository;
import com.devFabricio.movieflix.services.exceptions.ResourceNotFoundExceptions;

@Service
public class GenerService {

	 @Autowired
	    private GenreRepository genreRepository;

	  public List<GenreDTO> findAllGenres() {
	        return genreRepository.findAll()
	                .stream()
	                .map(GenreDTO::new)
	                .collect(Collectors.toList());
	    }
	  
	  @Transactional(readOnly = true)
		public GenreDTO findById(Long id) {
			Optional<Genre> obj = genreRepository.findById(id);
			return obj.map(GenreDTO::new).orElseThrow(() -> new ResourceNotFoundExceptions("Gênero não encontrado"));
		}
	
}

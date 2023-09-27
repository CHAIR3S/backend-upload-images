package com.ittiva.uploadImage.repository;

import com.ittiva.uploadImage.model.Image;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Integer>{
	
	Optional<Image> findByName(String name);

}

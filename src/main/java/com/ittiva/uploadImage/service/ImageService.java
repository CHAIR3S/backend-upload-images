package com.ittiva.uploadImage.service;


import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ittiva.uploadImage.model.Image;
import com.ittiva.uploadImage.util.ImageUtil;
import com.ittiva.uploadImage.repository.ImageRepository;

import jakarta.transaction.Transactional;

@Service
public class ImageService {
	
	@Autowired
	ImageRepository imageRepository;
	
	@Transactional //Revert every action when something goes wrong
	public ResponseGC uploadImage(MultipartFile image) throws IOException {
		ResponseGC response = new ResponseGC<>();
	
		
		//Necesita @Builder de la entidad		
		imageRepository.save(Image.builder()
                .name(image.getOriginalFilename())
                .imageData(ImageUtil.compressImage(image.getBytes())).build());
		
		response.setMessage("Succesfully saved image " + image.getOriginalFilename());
		response.setData(image);
		
		return response;
	}
	
	
	@Transactional
	public byte[] getImageByName(String name) throws IOException {
		Optional<Image> imageOptional = imageRepository.findByName(name);
		
		byte[] image = imageOptional.get().getImageData();
		
		return image;
	}

}

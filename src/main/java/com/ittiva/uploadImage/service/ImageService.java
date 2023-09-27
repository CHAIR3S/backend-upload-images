package com.ittiva.uploadImage.service;


import java.io.IOException;
import java.util.List;
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
	public ResponseGC uploadImage(Image image) throws IOException {
		ResponseGC response = new ResponseGC<>();
	
		response.setData(imageRepository.save(image));
		response.setMessage("Succesfully saved image " + image.getName());
		
		return response;
	}
	
	@Transactional
    public ResponseGC viewAll() {
		ResponseGC response = new ResponseGC<Image>();
		
		List<Image> imageList = (List<Image>) imageRepository.findAll();

		if(imageList.size()>0) {
			response.setList(imageList);
			response.setCount(imageList.size());
			response.setMessage("Encontradas");
		}
	
		
        return response;
    }
	
	@Transactional
	public ResponseGC getImageByName(String name) throws IOException {
		ResponseGC response = new ResponseGC<Image>();
		Optional<Image> imageOptional = imageRepository.findByName(name);
		
		if(!imageOptional.isEmpty()) {
			response.setData(imageOptional.get());
			response.setMessage("Encontrada");
		}

		
		return response;
	}

}

package com.ittiva.uploadImage.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ittiva.uploadImage.model.Image;
import com.ittiva.uploadImage.service.ImageService;
import com.ittiva.uploadImage.service.ResponseGC;
import com.ittiva.uploadImage.util.ImageUtil;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/image")
public class ImageController {

	
	@Autowired
	ImageService imageService;
	
	@PostMapping
	public ResponseEntity<ResponseGC<Image>> uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
		ResponseGC<Image> response = new ResponseGC();
		
		response = imageService.uploadImage(image);
		
		//return ResponseEntity.status(HttpStatus.OK).body(response);
		return new ResponseEntity<ResponseGC<Image>>(response, HttpStatus.OK);
	}

	
	
	@GetMapping("/{name}")
	public ResponseEntity<byte[]> getImageByName(@PathVariable("name") String name) throws IOException{
		
		
        byte[] imageData = imageService.getImageByName(name); // Obtiene el array de bytes de la imagen
        
        if (imageData == null) return ResponseEntity.notFound().build();

        try {
            BufferedImage bufferedImage = ImageUtil.convertBytesToImage(imageData);

            // Convierte BufferedImage a byte[] para la respuesta
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                ImageIO.write(bufferedImage, "jpg", bos);
                byte[] imageBytes = bos.toByteArray();
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build(); // Retorna 500 si ocurre un error al leer la imagen
        }
    }
		
//		byte[] image = imageService.getImageByName(name);
//		
//		
//		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(image);
	
}

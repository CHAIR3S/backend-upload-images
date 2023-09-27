package com.ittiva.uploadImage.model;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Image")
@Data
@Builder //Necesita tener @NoArgsConstructor y @AllArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pkImage")
	private Integer id;
	

	@Column(name = "nameImage")
	private String name;

	@Lob
	@Column(name = "imagedata", length = 100000) //Maximo tamaño de imagenes
	private Blob image;
	

}

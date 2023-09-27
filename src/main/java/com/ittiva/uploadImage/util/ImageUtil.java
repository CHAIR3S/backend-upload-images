package com.ittiva.uploadImage.util;

import javax.imageio.ImageIO;

import com.ittiva.uploadImage.model.Image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

public class ImageUtil {

    public static byte[] compressImage(byte[] inputBytes) throws IOException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(inputBytes);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
             
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            BufferedImage compressedImage = new BufferedImage(100, 100, bufferedImage.getType()); // o el tamaño que prefieras
            
            ImageIO.write(compressedImage, "jpg", outputStream); // usa "png" si prefieres PNG
            
            return outputStream.toByteArray();
        }
    }
    
    
    public static BufferedImage convertBytesToImage(byte[] imageData) throws IOException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData)) {
            return ImageIO.read(inputStream);
        }
    }
    

    
}


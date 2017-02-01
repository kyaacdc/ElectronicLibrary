package com.el.spring.controller.assistant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//import com.el.spring.util.PropertyAccessor;
import org.springframework.util.FileCopyUtils;

public class FileValidator {

    public static boolean hasBookFormat(FileModel file){
        String filename = file.getFile().getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        return extension.equals("txt") || extension.equals("rtf") || extension.equals("doc")
                || extension.equals("odt") || extension.equals("pdf") || extension.equals("docx")
                && !extension.equals("jpg") && !extension.equals("png") && !extension.equals("bmp");
    }

    public static boolean hasImageFormat(FileModel file){
        String filename = file.getFile().getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        return extension.equals("jpg") || extension.equals("png") || extension.equals("bmp");
    }

    public static boolean hasValidImageResolution(FileModel file, String uploadPath) throws IOException {
        String filename = file.getFile().getOriginalFilename();
        File image = new File(uploadPath + filename);
        FileCopyUtils.copy(file.getFile().getBytes(), image);
        BufferedImage bimg = ImageIO.read(image);
        int width = bimg.getWidth();
        int height= bimg.getHeight();
        return width < 600 && height < 800 && hasImageFormat(file);
    }
}

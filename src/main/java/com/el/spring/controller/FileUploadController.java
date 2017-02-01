package com.el.spring.controller;

import com.el.spring.controller.assistant.*;
import com.el.spring.entity.Book;
import com.el.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    @Autowired
    ServletContext context;

    @Autowired
    private BookService bookService;

    private Book book;
    private int isImage;

    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public ModelAndView fileUploadPage(@RequestParam("id") int id,
                                       @RequestParam("isimage") int isimage) {
        isImage = isimage;
        book = bookService.getBookById(id);
        FileModel file = new FileModel();
        ModelAndView modelAndView = new ModelAndView("fileUpload", "command", file);
        return modelAndView;
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String fileUpload(@Validated FileModel file, BindingResult result, ModelMap model) throws IOException {

        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "fileUpload";
        } else {
            System.out.println("Fetching file");
        }
            MultipartFile multipartFile = file.getFile();


            String uploadPath = "";
            String originalFilename = "";
            String path = "";
            String fileName = "";


        int width = 1000;


        int height = 1000;

                uploadPath = (context.getRealPath("") + "resources/images" + File.separator);

                if(FileValidator.hasImageFormat(file)  && (isImage == 1) ) {
                    //String filename = file.getFile().getOriginalFilename();
                    //File image = new File(uploadPath + filename);
                    //FileCopyUtils.copy(file.getFile().getBytes(), image);
                    //BufferedImage bimg = ImageIO.read(image);
                    //width = bimg.getWidth();
                    //height = bimg.getHeight();

                    if (FileValidator.hasValidImageResolution(file, uploadPath)) {
                        originalFilename = file.getFile().getOriginalFilename();
                        path = uploadPath + originalFilename;
                        FileCopyUtils.copy(file.getFile().getBytes(), new File(path));
                        fileName = multipartFile.getOriginalFilename();
                        model.addAttribute("fileName", fileName);
                        model.addAttribute("isImage");
                        book.setImage("/resources/images/" + originalFilename);
                        bookService.updateBook(book);
                        return "success";
                    } else
                        return "imageNotValid";
                }
/*
                if ((width < 600) && (height < 800) && (isImage == 1)){
                    originalFilename = file.getFile().getOriginalFilename();
                    path = uploadPath + originalFilename;
                    FileCopyUtils.copy(file.getFile().getBytes(), new File(path));
                    fileName = multipartFile.getOriginalFilename();
                    model.addAttribute("fileName", fileName);
                    model.addAttribute("isImage");
                    book.setImage("/resources/images/" + originalFilename);
                    bookService.updateBook(book);
                    return "success";
                }  else if ((isImage == 1))
                    return "imageNotValid";
                    */

               // width < 600 && height < 800 && hasImageFormat(file);






         if (FileValidator.hasBookFormat(file)  && (isImage == 0)) {
            uploadPath = (context.getRealPath("") + "resources/books" + File.separator);

            originalFilename = file.getFile().getOriginalFilename();
                path = uploadPath + originalFilename;
                FileCopyUtils.copy(file.getFile().getBytes(), new File(path));
                fileName = multipartFile.getOriginalFilename();
                model.addAttribute("fileName", fileName);
                book.setPath("/resources/books/" + originalFilename);
             bookService.updateBook(book);

             return "success";
            }

            return "imageNotValid";
    }

}
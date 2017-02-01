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

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    @Autowired
    ServletContext context;

    @Autowired
    private BookService bookService;

    private Book book;
    boolean isImage = false;

    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public ModelAndView fileUploadPage(@RequestParam("id") int id,
                                       @RequestParam("isimage") int isimage) {
        isImage = (isimage == 1);
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
            MultipartFile multipartFile = file.getFile();

            String uploadPath = "";
            String originalFilename = "";
            String path = "";
            String fileName = "";

            if (isImage) {
                uploadPath = (context.getRealPath("") + "resources/images" + File.separator);
                if (FileValidator.hasValidImageResolution(file, uploadPath)) {
                    originalFilename = file.getFile().getOriginalFilename();
                    path = uploadPath + originalFilename;
                    FileCopyUtils.copy(file.getFile().getBytes(), new File(path));
                    fileName = multipartFile.getOriginalFilename();
                    model.addAttribute("fileName", fileName);
                    book.setImage("/resources/images/" + originalFilename);
                } else
                    return "imageNotValid";
            } else if (FileValidator.hasBookFormat(file)) {
                uploadPath = (context.getRealPath("") + "resources/books" + File.separator);
                originalFilename = file.getFile().getOriginalFilename();
                path = uploadPath + originalFilename;
                FileCopyUtils.copy(file.getFile().getBytes(), new File(path));
                fileName = multipartFile.getOriginalFilename();
                model.addAttribute("fileName", fileName);
                book.setPath("/resources/books/" + originalFilename);
            } else
                return "Not valid format";

            bookService.updateBook(book);

            return "success";
        }
    }

}
package com.el.spring.controller;

import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.el.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

@Controller
public class FileDownloadController {

    @Autowired
    private ServletContext context;

    @Autowired
    private BookService bookService;

    private String filepath = "";

   @RequestMapping(value={"/fileDownload"}, method = RequestMethod.GET)
   public String getHomePage(@RequestParam("id") int id,
                             @RequestParam("isimage") int isImage, ModelMap model) {
       filepath = (isImage == 1) ? bookService.getBookById(id).getImage() : bookService.getBookById(id).getPath();
       return "fileDownload";
    }

    /*
     * Download a file from
     *   inside project, located in resources folder.
     */
    @RequestMapping(value="/download/{type}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, @PathVariable("type") String type) throws IOException {

        File file = new File(context.getRealPath("") + filepath);

        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : "+mimeType);

        response.setContentType(mimeType);

        /* "Content-Disposition : inline (but maybe - attachment) " will show viewable types [like images/text/pdf/anything viewable by browser] right on browser
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        if(type.equalsIgnoreCase("externalview"))
            response.setHeader("Content-Disposition", String.format("inline ; filename=\"" + file.getName() +"\""));
        else
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));


        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

}

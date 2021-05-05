package com.example.htmlpdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class HomeController {


    @RequestMapping(value = {"","/"})
    public String hello()
    {
        return "index";
    }

    @RequestMapping("/pdf")
    public String hellopdf()
    {
        return "index";
    }

    @RequestMapping(path = "/pdf/download")
    public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        ByteArrayOutputStream target = new ByteArrayOutputStream();

        /*Setup converter properties. */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        /* Call convert method */
        HtmlConverter.convertToPdf(Converter.convertToHtml(request,"/pdf"), target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();


        /* Send the response as downloadable PDF */

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);


    }

}

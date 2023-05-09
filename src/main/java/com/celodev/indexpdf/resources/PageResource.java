package com.celodev.indexpdf.resources;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.celodev.indexpdf.dtos.PdfPageDTO;
import com.celodev.indexpdf.services.PageService;
import com.celodev.indexpdf.services.PdfService;

@RestController
@CrossOrigin
@RequestMapping("api/index")
public class PageResource {

    @Autowired
    private PageService service;
    @Autowired
    PdfService pdfService;

    @PostMapping
    public void indexAllPages(@RequestBody MultipartFile pdf) throws IOException {
        service.indexAll(pdfService.extractAllPagesContent(pdf.getInputStream()));
    }

    @PostMapping("{page}")
    public void indexPage(
            @PathVariable int page, @RequestBody MultipartFile pdf) throws IOException {

                System.out.println(pdf.getOriginalFilename());
        PdfPageDTO dto = pdfService.extractPageContent(pdf.getInputStream(), page);

        service.index(dto);
    }
}

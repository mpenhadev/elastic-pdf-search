package com.celodev.indexpdf.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.celodev.indexpdf.dtos.PdfPageDTO;
import com.celodev.indexpdf.entities.PageDocument;
import com.celodev.indexpdf.repositories.PageRepository;
import com.celodev.indexpdf.services.PageService;

@Service
public class PageServiceImpl implements PageService {

  @Autowired PageRepository repository;

  @Override
  public PageDocument index(PdfPageDTO dto) throws IOException {

    System.out.println(dto.toString());
    return repository.save(dto.toPageDocument());
  }

  @Override
  public void deleteAll() {
    repository.deleteAll();
  }

  @Override
  public void indexAll(List<PdfPageDTO> pdfPages) {

    List<PageDocument> pageDocuments = pdfPages
        .stream()
        .map(x -> PageDocument.builder()
          .title("title")
          .content(x.getPageContent())
          .page(x.getPageNumber())
          .build()
        )
        .collect(Collectors.toList());
        

        pageDocuments.forEach(page -> System.out.println(page.toString()));

        try {
          repository.saveAll(pageDocuments);
        } catch (Exception e) {
          e.printStackTrace();
        }
     
  }
}
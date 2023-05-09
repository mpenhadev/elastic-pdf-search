package com.celodev.indexpdf.services;

import java.io.IOException;
import java.util.List;

import com.celodev.indexpdf.dtos.PdfPageDTO;
import com.celodev.indexpdf.entities.PageDocument;
import com.itextpdf.text.pdf.codec.Base64.InputStream;

public interface PageService {
  PageDocument index(PdfPageDTO dto) throws IOException;
  public void indexAll(List<PdfPageDTO> pdfpages);
  void deleteAll();
}
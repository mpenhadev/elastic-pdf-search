package com.celodev.indexpdf.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.celodev.indexpdf.dtos.PdfPageDTO;

public interface PdfService {
  List<PdfPageDTO> extractAllPagesContent(InputStream pdfInputStream) throws IOException;

  String satinizePageContent(String pageContent);

  PdfPageDTO extractPageContent(InputStream pdfInputStream, int page) throws IOException;
}

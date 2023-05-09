package com.celodev.indexpdf.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.celodev.indexpdf.dtos.PdfPageDTO;
import com.celodev.indexpdf.services.PdfService;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

@Service
public class PdfServiceImpl implements PdfService {

    private static final Pattern REPLACE_PATTERN = Pattern.compile("[\\-$]|^ +| +$|( )+");

    @Override
    public List<PdfPageDTO> extractAllPagesContent(InputStream pdfInputStream) throws IOException {

        PdfReader pdfReader = new PdfReader(pdfInputStream);
        PdfReaderContentParser pdfParser = new PdfReaderContentParser(pdfReader);
        List<PdfPageDTO> pdfPages = new ArrayList<>();

        int TOTAL_PAGES = pdfReader.getNumberOfPages();

        for (int CURRENT_PAGE = 1; CURRENT_PAGE <= TOTAL_PAGES; CURRENT_PAGE++) {
            String PAGE_CONTENT = pdfParser
                    .processContent(CURRENT_PAGE, new SimpleTextExtractionStrategy())
                    .getResultantText();

            String SANITIZED_CONTENT = satinizePageContent(PAGE_CONTENT);

            PdfPageDTO pdfPage = PdfPageDTO.builder()
                    .pageNumber(CURRENT_PAGE)
                    .pageContent(SANITIZED_CONTENT)
                    .build();
            pdfPages.add(pdfPage);

        }
        pdfReader.close();
        return pdfPages;
    }

    @Override
    public PdfPageDTO extractPageContent(InputStream pdfInputStream, int page) throws IOException {

        PdfReader pdfReader = new PdfReader(pdfInputStream);
        PdfReaderContentParser pdfParser = new PdfReaderContentParser(pdfReader);
 

            String PAGE_CONTENT = pdfParser
                    .processContent(page, new SimpleTextExtractionStrategy())
                    .getResultantText();

            System.out.println(PAGE_CONTENT);

            String SANITIZED_CONTENT = satinizePageContent(PAGE_CONTENT);

            return PdfPageDTO.builder()
                    .pageNumber(page)
                    .pageContent(SANITIZED_CONTENT)
                    .build();
    }

    public String satinizePageContent(String pageContent) {
        return pageContent.replaceAll("[\\-$]", "")
                .replace("\n", " ")
                .replaceAll("^ +| +$|( )+", " ");
    }
}

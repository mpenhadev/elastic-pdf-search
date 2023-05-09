package com.celodev.indexpdf.dtos;

import com.celodev.indexpdf.entities.PageDocument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PdfPageDTO {
    private int pageNumber;
    private String pageContent;
    private String title;

    public static PageDocument toPageDocument(PdfPageDTO pdfPage){
        return PageDocument.builder() 
            .content(pdfPage.getPageContent())
            .page(pdfPage.getPageNumber())
            .build();
    }

    public  PageDocument toPageDocument(){
        return PageDocument.builder() 
            .content(this.getPageContent())
            .page(this.getPageNumber())
            .build();
    }
}

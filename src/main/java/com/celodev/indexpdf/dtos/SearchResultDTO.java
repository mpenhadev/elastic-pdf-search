package com.celodev.indexpdf.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.elasticsearch.core.SearchHit;

import com.celodev.indexpdf.entities.PageDocument;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchResultDTO {
    private String id;
    private String content;
    private int page;
    private List<List<String>> fragments;

    public SearchResultDTO(PageDocument document) {
        this.id = document.getId();
        this.content = document.getContent();
        this.page = document.getPage();
    }

    public static SearchResultDTO fromSearchHit(SearchHit<PageDocument> searchHit) {
        SearchResultDTO searchResultDTO = new SearchResultDTO(searchHit.getContent());
        List<List<String>> highlights = searchHit
                .getHighlightFields()
                .values()
                .stream()
                .collect(Collectors.toList());
        searchResultDTO.setFragments(highlights);
        return searchResultDTO;
    }
}

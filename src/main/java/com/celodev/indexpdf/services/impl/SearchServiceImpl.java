package com.celodev.indexpdf.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.stereotype.Service;

import com.celodev.indexpdf.dtos.SearchFilterDTO;
import com.celodev.indexpdf.dtos.SearchResultDTO;
import com.celodev.indexpdf.entities.PageDocument;
import com.celodev.indexpdf.repositories.PageRepository;
import com.celodev.indexpdf.services.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    PageRepository repository;

    @Override
    public Page<PageDocument> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<SearchResultDTO> findFiltered(SearchFilterDTO filter) {

        Pageable pageRequest = PageRequest.of(filter.getPage(), filter.getSize());
        SearchPage<PageDocument> pages = repository.findByContent(filter.getTerm(), pageRequest);
        return convertSearchPageToPage(pages, pageRequest);

    }

    private Page<SearchResultDTO> convertSearchPageToPage(SearchPage<PageDocument> searchPage, Pageable pageable) {
        List<SearchResultDTO> searchResultsDTO = searchPage.getSearchHits()
                .stream()
                .map(SearchResultDTO::fromSearchHit)
                .collect(Collectors.toList());

        return new PageImpl<>(searchResultsDTO, pageable, searchPage.getTotalElements());
    }
}

package com.celodev.indexpdf.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.celodev.indexpdf.dtos.SearchFilterDTO;
import com.celodev.indexpdf.dtos.SearchResultDTO;
import com.celodev.indexpdf.entities.PageDocument;

public interface SearchService {
    Page<SearchResultDTO> findFiltered(SearchFilterDTO filter);
    Page<PageDocument> findAll(Pageable pageable);

}

package com.celodev.indexpdf.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celodev.indexpdf.dtos.SearchFilterDTO;
import com.celodev.indexpdf.dtos.SearchResultDTO;
import com.celodev.indexpdf.services.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
@CrossOrigin
@RequestMapping("api/search")
public class SearchResource {

    @Autowired SearchService service;

    @GetMapping
    public Page<SearchResultDTO> search(@ModelAttribute SearchFilterDTO filter) {
        return service.findFiltered(filter);
    }
}

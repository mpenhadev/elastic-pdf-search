package com.celodev.indexpdf.repositories;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.celodev.indexpdf.entities.PageDocument;


public interface PageRepository extends ElasticsearchRepository<PageDocument, String> {

    @Highlight(fields = {@HighlightField(name = "content")})
    SearchPage<PageDocument> findByContent(String termo,Pageable pageable);

}

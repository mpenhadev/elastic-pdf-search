package com.celodev.indexpdf.entities;

import org.springframework.data.elasticsearch.annotations.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "index-pdf")
public class PageDocument {
  @Id
  private String id;
  private int page;
  private String content;
}

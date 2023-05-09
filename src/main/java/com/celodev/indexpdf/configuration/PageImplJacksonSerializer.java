package com.celodev.indexpdf.configuration;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.PageImpl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class PageImplJacksonSerializer extends JsonSerializer<PageImpl> {

   @Override
   public void serialize(PageImpl page, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
    
    jsonGenerator.writeStartObject();
    jsonGenerator.writeObjectField("content", page.getContent());
    jsonGenerator.writeNumberField("page", page.getNumber());
    jsonGenerator.writeNumberField("size", page.getNumberOfElements());
    jsonGenerator.writeNumberField("totalPages", page.getTotalPages());
    jsonGenerator.writeNumberField("total", page.getTotalElements());
    jsonGenerator.writeEndObject();
  }

}
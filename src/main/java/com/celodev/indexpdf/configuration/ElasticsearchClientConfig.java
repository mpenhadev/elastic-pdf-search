package com.celodev.indexpdf.configuration;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.celodev.indexpdf.repositories")
@ComponentScan(basePackages = { "com.celodev.indexpdf" })
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {

    @Value("${spring.elasticsearch.rest.uri}")
    private String HOST_URL_CLOUD;
    
    @Value("${spring.elasticsearch.rest.username}")
    private String USERNAME_ELASTIC;

    @Value("${spring.elasticsearch.rest.password}")
    private String PASSWORD_ELASTIC;

    @Value("${spring.elasticsearch.rest.connection-timeout}")
    private int TIME_OUT;

    @Bean
    public RestHighLevelClient elasticsearchClient() {

        System.out.println(PASSWORD_ELASTIC);

        HttpHeaders compatibilityHeaders = new HttpHeaders();
        compatibilityHeaders.add("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
        compatibilityHeaders.add("Content-Type", 
        "application/vnd.elasticsearch+json;" + "compatible-with=7");
                
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(HOST_URL_CLOUD)
                .usingSsl()
                .withDefaultHeaders(compatibilityHeaders) // this variant for imperative code
                .withBasicAuth(USERNAME_ELASTIC, PASSWORD_ELASTIC)
                .withConnectTimeout(TIME_OUT)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

}

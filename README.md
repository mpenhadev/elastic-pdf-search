# Simple Elastic PDF Search

<p align="start">
  <img width="220px" src="https://mkyong.com/wp-content/uploads/2017/03/spring-data-elasticsearch-logo.png" alt="Spring Data Elasticsearch Logo">
</p>

## Introduction
<p >
This simple API allows users to upload PDF documents and extract the content from each page using the iText library. This way, it is possible to index the page content as a single document in Elasticsearch, making it searchable and easily retrievable using parameters such as page number and search term.
</p>

## Motivation
>  Working with PDF files in Elasticsearch can be challenging due to the lack of built-in support for extracting page numbers and their content. Although Elasticsearch's native PDF processor can transform PDF files into base64 and ingest its, it does not provide accurate information about the number of pages, making it difficult to perform full-text searches on specific pages. Using the iText library can solve this problem by allowing extraction of content from specific pages, which can be indexed in Elasticsearch. This project simplifies indexing of PDF content, providing a simple API that allows users to upload PDF files, extract the content of each page using the iText library, and index their content as a single document in Elasticsearch, making it searchable and easily retrievable through parameters such as page number and search term.


#### Why extract page contents and index?


## Technologies
The project was developed using the following technologies:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data Elasticsearch](https://spring.io/projects/spring-data-elasticsearch)
- [iText Library](https://itextpdf.com/)

## Configuration

To run the project, Elasticsearch must be installed on the machine. It can be downloaded from the official website: https://www.elastic.co/downloads/elasticsearch.

To connect to Elasticsearch, you need to configure the application.yml file located in the src/main/resources folder. The following properties should be set:

```yaml
spring:
  data:
    elasticsearch:
      cluster-name: my-cluster
      cluster-nodes: localhost:9300
```

> <i> obs: If necessary, these properties can be changed according to the Elasticsearch configuration on your machine. </i>

## Endpoints

The API has the following endpoints:

| Endpoint | HTTP Method | Request Body | Parameters | Description |
| --- | --- | --- | --- | --- |
| `/api/index` | POST | PDF file | - | Indexes the pages of the provided PDF file in Elasticsearch. |
| `/api/search` | GET | - | `page`: page number (starting from 0).<br>`size`: number of results per page.<br>`term`: search term. | Searches for a term in all indexed pages. Returns paginated results. |

## Indexing a PDF document

**Endpoint**: `POST /api/index`

This endpoint receives a PDF file and indexes it in Elasticsearch, separating each page into an individual document. The PDF file must be sent as a `multipart/form-data`, using the key `file`.

## Searching for a term in all pages

**Endpoint**: `GET /api/search`

This endpoint searches for a term in all indexed pages in Elasticsearch. It is possible to paginate the results using the `page` and `size` parameters, which respectively define the desired page and the number of results per page. The searched term must be sent in the `term` parameter.

This endpoint returns a JSON object containing the search results.Each object in the result represents a page of the document, containing the following information:

```
{
  "documentId": {elasticseach document id}
  "page": {pdf page}
  "contet": {the entire content of the page}
  "fragments": {Array of fragments containing the searched term}
},
```

> Here is an example of request for search inside index.

```curl
curl -X GET "http://{host}/api/search?term=foo"
```
> And that's the response:

```json
{
  "page": 1,
  "contet": "Pdf page content",
  "fragments": [ 
      "Lorem ipsum dolor sit amet, consectetur adipiscing <em>foo</em>",
      "elit. In dictum sem eu augue tincidunt <em>foo</em> tempus. Vestibulum ante ipsum"
  ]
},
{
  "page": 2,
  "contet": "Pdf page content",
  "fragments": [ 
      "Lorem ipsum dolor sit amet, consectetur adipiscing <em>foo</em>",
      "elit. In dictum sem eu augue tincidunt <em>foo</em> tempus. Vestibulum ante ipsum"
  ]
}
```
### Author
Marcelo Penha

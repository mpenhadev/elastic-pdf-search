# Simple Elastic PDF Search

<p align="start">
  <img width="220px" src="https://mkyong.com/wp-content/uploads/2017/03/spring-data-elasticsearch-logo.png" alt="Spring Data Elasticsearch Logo">
</p>

## Introduction
<p >
This simple API allows users to upload PDF documents, extract the content from each page, and index each page as a separate Elasticsearch document. It facilitates the querying process and returns a JSON with the search results for a term within the text content. Each JSON element will contain the page where that term was found and will also highlight the fragment where the term is contained on the page content.
</p>

### Author
Marcelo Penha

## Motivation
>  Working with PDF files in Elasticsearch can be challenging due to the lack of built-in support for extracting page numbers and their content. Although Elasticsearch's native PDF processor can transform PDF files into base64 and ingest its, it does not provide accurate information about the number of pages, making it difficult to perform full-text and retrieve good results. This solution allows us to obtain important information about the processed PDF, such as the total number of pages in the document, the page number being processed, and its content, making it possible to create an elasticsearch document for each page with these fields, simplifying the querying process.

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


package com.sa.gms.restclient.chromadb.connection;

import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Interface for REST client operations using RestTemplate.
 * Supports GET, POST, PATCH, PUT, and DELETE HTTP methods.
 */
public interface IRestClient {

    /**
     * Performs a GET request to the specified URL.
     *
     * @param url the URL to send the GET request to
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> get(String url);

    /**
     * Performs a GET request to the specified URL with query parameters.
     *
     * @param url the URL to send the GET request to
     * @param queryParams the query parameters to append to the URL
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> get(String url, String queryParams);

    /**
     * Performs a GET request to the specified URL with custom headers.
     *
     * @param url the URL to send the GET request to
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> get(String url, Map<String, String> headers);

    /**
     * Performs a GET request to the specified URL with query parameters and custom headers.
     *
     * @param url the URL to send the GET request to
     * @param queryParams the query parameters to append to the URL
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> get(String url, String queryParams, Map<String, String> headers);

    /**
     * Performs a POST request to the specified URL with a request body.
     *
     * @param url the URL to send the POST request to
     * @param requestBody the request body as String
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> post(String url, String requestBody);

    /**
     * Performs a POST request to the specified URL without a request body.
     *
     * @param url the URL to send the POST request to
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> post(String url);

    /**
     * Performs a POST request to the specified URL with a request body and custom headers.
     *
     * @param url the URL to send the POST request to
     * @param requestBody the request body as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> post(String url, String requestBody, Map<String, String> headers);

    /**
     * Performs a PATCH request to the specified URL with a request body.
     *
     * @param url the URL to send the PATCH request to
     * @param requestBody the request body as String
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> patch(String url, String requestBody);

    /**
     * Performs a PATCH request to the specified URL without a request body.
     *
     * @param url the URL to send the PATCH request to
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> patch(String url);

    /**
     * Performs a PATCH request to the specified URL with a request body and custom headers.
     *
     * @param url the URL to send the PATCH request to
     * @param requestBody the request body as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> patch(String url, String requestBody, Map<String, String> headers);

    /**
     * Performs a PUT request to the specified URL with a request body.
     *
     * @param url the URL to send the PUT request to
     * @param requestBody the request body as String
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> put(String url, String requestBody);

    /**
     * Performs a PUT request to the specified URL with a request body and custom headers.
     *
     * @param url the URL to send the PUT request to
     * @param requestBody the request body as String
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> put(String url, String requestBody, Map<String, String> headers);

    /**
     * Performs a DELETE request to the specified URL.
     *
     * @param url the URL to send the DELETE request to
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> delete(String url);

    /**
     * Performs a DELETE request to the specified URL with query parameters.
     *
     * @param url the URL to send the DELETE request to
     * @param queryParams the query parameters to append to the URL
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> delete(String url, String queryParams);

    /**
     * Performs a DELETE request to the specified URL with custom headers.
     *
     * @param url the URL to send the DELETE request to
     * @param headers the HTTP headers to include in the request
     * @return ResponseEntity containing the response as String
     */
    ResponseEntity<String> delete(String url, Map<String, String> headers);
}
package com.sa.gms.restclient.chromadb.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of IRestClient using RestTemplate.
 * Provides methods for GET, POST, PATCH, PUT, and DELETE HTTP operations.
 */
@Component
public class ChromaRestClientImpl implements IRestClient {

    private static final Logger logger = LoggerFactory.getLogger(ChromaRestClientImpl.class);
    
    private final RestTemplate restTemplate;

    /**
     * Constructor that initializes RestTemplate.
     */
    public ChromaRestClientImpl() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Constructor that accepts a custom RestTemplate instance.
     *
     * @param restTemplate the RestTemplate instance to use
     */
    public ChromaRestClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> get(String url) {
        return get(url, new HashMap<>());
    }

    @Override
    public ResponseEntity<String> get(String url, String queryParams) {
        String fullUrl = queryParams != null && !queryParams.isEmpty()
            ? url + "?" + queryParams
            : url;
        
        logger.info("Starting GET request to URL: {}", fullUrl);
        logger.debug("GET request - URL: {}, Headers: {}", fullUrl, MediaType.APPLICATION_JSON);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                fullUrl,
                HttpMethod.GET,
                entity,
                String.class
            );
            
            logger.info("Received response from GET request - URL: {}, Status: {}", fullUrl, response.getStatusCode());
            logger.debug("GET response - URL: {}, Status: {}, Response: {}", fullUrl, response.getStatusCode(), response.getBody());
            
            return response;
        } catch (RestClientException e) {
            logger.error("HTTP error during GET request to URL: {}, Error: {}", fullUrl, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during GET request to URL: {}, Error: {}", fullUrl, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> get(String url, Map<String, String> headers) {
        return get(url, null, headers);
    }

    @Override
    public ResponseEntity<String> get(String url, String queryParams, Map<String, String> headers) {
        String fullUrl = queryParams != null && !queryParams.isEmpty()
            ? url + "?" + queryParams
            : url;
        
        logger.info("Starting GET request to URL: {}", fullUrl);
        logger.debug("GET request - URL: {}, Headers: {}", fullUrl, headers);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        
        // Add custom headers if provided
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }
        
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                fullUrl,
                HttpMethod.GET,
                entity,
                String.class
            );
            
            logger.info("Received response from GET request - URL: {}, Status: {}", fullUrl, response.getStatusCode());
            logger.debug("GET response - URL: {}, Status: {}, Response: {}", fullUrl, response.getStatusCode(), response.getBody());
            
            return response;
        } catch (RestClientException e) {
            logger.error("HTTP error during GET request to URL: {}, Error: {}", fullUrl, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during GET request to URL: {}, Error: {}", fullUrl, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> post(String url) {
        return post(url, null);
    }

    @Override
    public ResponseEntity<String> post(String url, String requestBody) {
        logger.info("Starting POST request to URL: {}", url);
        logger.debug("POST request - URL: {}, Headers: {}, Payload: {}", url, MediaType.APPLICATION_JSON, requestBody);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
            );
            
            logger.info("Received response from POST request - URL: {}, Status: {}", url, response.getStatusCode());
            logger.debug("POST response - URL: {}, Status: {}, Response: {}", url, response.getStatusCode(), response.getBody());
            
            return response;
        } catch (RestClientException e) {
            logger.error("HTTP error during POST request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during POST request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> post(String url, String requestBody, Map<String, String> headers) {
        logger.info("Starting POST request to URL: {}", url);
        logger.debug("POST request - URL: {}, Headers: {}, Payload: {}", url, headers, requestBody);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        
        // Add custom headers if provided
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }
        
        HttpEntity<String> entity = new HttpEntity<>(requestBody, httpHeaders);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
            );
            
            logger.info("Received response from POST request - URL: {}, Status: {}", url, response.getStatusCode());
            logger.debug("POST response - URL: {}, Status: {}, Response: {}", url, response.getStatusCode(), response.getBody());
            
            return response;
        } catch (RestClientException e) {
            logger.error("HTTP error during POST request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during POST request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> patch(String url) {
        return patch(url, null);
    }

    @Override
    public ResponseEntity<String> patch(String url, String requestBody) {
        logger.info("Starting PATCH request to URL: {}", url);
        logger.debug("PATCH request - URL: {}, Headers: {}, Payload: {}", url, MediaType.APPLICATION_JSON, requestBody);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PATCH,
                entity,
                String.class
            );
            
            logger.info("Received response from PATCH request - URL: {}, Status: {}", url, response.getStatusCode());
            logger.debug("PATCH response - URL: {}, Status: {}, Response: {}", url, response.getStatusCode(), response.getBody());
            
            return response;
        } catch (RestClientException e) {
            logger.error("HTTP error during PATCH request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during PATCH request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> patch(String url, String requestBody, Map<String, String> headers) {
        logger.info("Starting PATCH request to URL: {}", url);
        logger.debug("PATCH request - URL: {}, Headers: {}, Payload: {}", url, headers, requestBody);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        
        // Add custom headers if provided
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }
        
        HttpEntity<String> entity = new HttpEntity<>(requestBody, httpHeaders);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PATCH,
                entity,
                String.class
            );
            
            logger.info("Received response from PATCH request - URL: {}, Status: {}", url, response.getStatusCode());
            logger.debug("PATCH response - URL: {}, Status: {}, Response: {}", url, response.getStatusCode(), response.getBody());
            
            return response;
        } catch (RestClientException e) {
            logger.error("HTTP error during PATCH request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during PATCH request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> put(String url, String requestBody) {
        logger.info("Starting PUT request to URL: {}", url);
        logger.debug("PUT request - URL: {}, Headers: {}, Payload: {}", url, MediaType.APPLICATION_JSON, requestBody);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                String.class
            );
            
            logger.info("Received response from PUT request - URL: {}, Status: {}", url, response.getStatusCode());
            logger.debug("PUT response - URL: {}, Status: {}, Response: {}", url, response.getStatusCode(), response.getBody());
            
            return response;
        } catch (RestClientException e) {
            logger.error("HTTP error during PUT request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during PUT request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> put(String url, String requestBody, Map<String, String> headers) {
        logger.info("Starting PUT request to URL: {}", url);
        logger.debug("PUT request - URL: {}, Headers: {}, Payload: {}", url, headers, requestBody);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        
        // Add custom headers if provided
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }
        
        HttpEntity<String> entity = new HttpEntity<>(requestBody, httpHeaders);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                String.class
            );
            
            logger.info("Received response from PUT request - URL: {}, Status: {}", url, response.getStatusCode());
            logger.debug("PUT response - URL: {}, Status: {}, Response: {}", url, response.getStatusCode(), response.getBody());
            
            return response;
        } catch (RestClientException e) {
            logger.error("HTTP error during PUT request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during PUT request to URL: {}, Error: {}", url, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> delete(String url) {
        return delete(url, new HashMap<>());
    }

    @Override
    public ResponseEntity<String> delete(String url, String queryParams) {
        String fullUrl = queryParams != null && !queryParams.isEmpty()
            ? url + "?" + queryParams
            : url;
        
        logger.info("Starting DELETE request to URL: {}", fullUrl);
        logger.debug("DELETE request - URL: {}, Headers: {}", fullUrl, MediaType.APPLICATION_JSON);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                fullUrl,
                HttpMethod.DELETE,
                entity,
                String.class
            );
            
            logger.info("Received response from DELETE request - URL: {}, Status: {}", fullUrl, response.getStatusCode());
            logger.debug("DELETE response - URL: {}, Status: {}, Response: {}", fullUrl, response.getStatusCode(), response.getBody());
            
            return response;
        } catch (RestClientException e) {
            logger.error("HTTP error during DELETE request to URL: {}, Error: {}", fullUrl, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during DELETE request to URL: {}, Error: {}", fullUrl, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Performs a DELETE request to the specified URL with custom headers.
     *
     * @param url     the URL to send the DELETE request to
     * @param headerMap the HTTP headers to include in the request
     * @return ResponseEntity containing the response as String
     */
    @Override
    public ResponseEntity<String> delete(String url, Map<String, String> headerMap) {
        String fullUrl = url;
        
        logger.info("Starting DELETE request to URL: {}", fullUrl);
        logger.debug("DELETE request - URL: {}, Headers: {}", fullUrl, headerMap);
        
        HttpHeaders headers = new HttpHeaders();
        headerMap.entrySet().forEach(header -> {
            headers.put(
            header.getKey(),
                    Collections.singletonList(header.getValue()));

        });

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    fullUrl,
                    HttpMethod.DELETE,
                    entity,
                    String.class
            );
            
            logger.info("Received response from DELETE request - URL: {}, Status: {}", fullUrl, response.getStatusCode());
            logger.debug("DELETE response - URL: {}, Status: {}, Response: {}", fullUrl, response.getStatusCode(), response.getBody());
            
            return response;
        } catch (RestClientException e) {
            logger.error("HTTP error during DELETE request to URL: {}, Error: {}", fullUrl, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during DELETE request to URL: {}, Error: {}", fullUrl, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the RestTemplate instance being used.
     *
     * @return the RestTemplate instance
     */
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
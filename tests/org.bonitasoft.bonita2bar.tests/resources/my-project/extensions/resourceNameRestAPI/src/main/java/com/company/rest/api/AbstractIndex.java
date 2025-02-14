package com.company.rest.api;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.bonitasoft.web.extension.ResourceProvider;
import org.bonitasoft.web.extension.rest.RestAPIContext;
import org.bonitasoft.web.extension.rest.RestApiController;
import org.bonitasoft.web.extension.rest.RestApiResponse;
import org.bonitasoft.web.extension.rest.RestApiResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.rest.api.dto.Error;
import com.company.rest.api.dto.Result;
import com.company.rest.api.exception.ValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Parent Controller class to hide technical parts
 */
public abstract class AbstractIndex implements RestApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Index.class.getName());

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public ObjectMapper getMapper() {
        return mapper;
    }

    @Override
    public RestApiResponse doHandle(HttpServletRequest request, RestApiResponseBuilder responseBuilder, RestAPIContext context) {

        // Validate request
        try {
            validateInputParameters(request);
        } catch (ValidationException e) {
            LOGGER.error("Request for this REST API extension is not valid", e);
            return jsonResponse(responseBuilder, SC_BAD_REQUEST, Error.builder().message(e.getMessage()).build());
        }

        // Execute business logic
        Result result = execute(context);

        // Send the result as a JSON representation
        // You may use pagedJsonResponse if your result is multiple
        return jsonResponse(responseBuilder, SC_OK, result);
    }

    protected abstract Result execute(RestAPIContext context);

    protected abstract void validateInputParameters(HttpServletRequest request);

    /**
     * Build an HTTP response.
     *
     * @param responseBuilder the Rest API response builder
     * @param httpStatus      the status of the response
     * @param body            the response body
     * @return a RestAPIResponse
     */
    RestApiResponse jsonResponse(RestApiResponseBuilder responseBuilder, int httpStatus, Object body) {
        try {
            return responseBuilder
                    .withResponseStatus(httpStatus)
                    .withResponse(mapper.writeValueAsString(body))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to write body response as JSON: " + body, e);
        }
    }


    /**
     * Returns a paged result like Bonita BPM REST APIs.
     * Build a response with a content-range.
     *
     * @param responseBuilder the Rest API response builder
     * @param body            the response body
     * @param p               the page index
     * @param c               the number of result per page
     * @param total           the total number of results
     * @return a RestAPIResponse
     */
    RestApiResponse pagedJsonResponse(RestApiResponseBuilder responseBuilder, int httpStatus, Object body, int p, int c, long total) {
        try {
            return responseBuilder
                    .withContentRange(p, c, total)
                    .withResponseStatus(httpStatus)
                    .withResponse(mapper.writeValueAsString(body))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to write body response as JSON: " + body, e);
        }
    }

    /**
     * Load a property file into a java.util.Properties
     */
    protected Properties loadProperties(String fileName, ResourceProvider resourceProvider) {
        try (InputStream is = resourceProvider.getResourceAsStream(fileName)){
            Properties props = new Properties();
            props.load(is);
            return props;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties for REST API extension");
        }
    }
}

package com.company.rest.api;

import javax.servlet.http.HttpServletRequest;

import org.bonitasoft.web.extension.rest.RestAPIContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.company.rest.api.dto.Result;

/**
 * Controller class
 */
public class Index extends AbstractIndex {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Index.class.getName());

    /**
     * Ensure request is valid
     *
     * @param request the HttpRequest
     */
    @Override
    public void validateInputParameters(HttpServletRequest request) {
        // To retrieve query parameters use the request.getParameter(..) method.
        // Be careful, parameter values are always returned as String values
    }

    /**
     * Execute business logic
     *
     * @param context
     * @return Result
     */
    @Override
    protected Result execute(RestAPIContext context) {

        LOGGER.debug("Execute rest api call");

        /*
         * TODO: Execute business logic here, your code goes here
         */

        // Prepare the result
        return Result.builder().build();
    }
}
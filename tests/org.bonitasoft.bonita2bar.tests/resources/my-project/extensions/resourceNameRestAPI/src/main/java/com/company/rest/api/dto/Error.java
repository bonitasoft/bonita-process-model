package com.company.rest.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Error.ErrorBuilder.class)
public class Error {
    @JsonIgnore
    private final String name = "error";
    private final String message;

    public Error(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class ErrorBuilder {

        private String message;

        public ErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Error build() {
            return new Error(message);
        }

    }

    public static ErrorBuilder builder() {
        return new ErrorBuilder();
    }
}
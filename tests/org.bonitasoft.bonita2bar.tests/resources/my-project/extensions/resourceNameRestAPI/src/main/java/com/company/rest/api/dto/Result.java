package com.company.rest.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Result.ResultBuilder.class)
public class Result {
    @JsonIgnore
    private final LocalDate currentDate = LocalDate.now();
    
    public Result() {
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class ResultBuilder {

        public Result build() {
            return new Result();
        }
    }

    public static ResultBuilder builder() {
        return new ResultBuilder();
    }
}


package com.zambeyzz.MovieAPIDevelopment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidReleaseYearException extends ResponseStatusException {

    public InvalidReleaseYearException() {
        super(HttpStatus.BAD_REQUEST, "Release Year cannot bigger than this year");
    }
}


package com.felipemcassiano.UrlShortener.controllers;

import com.felipemcassiano.UrlShortener.services.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("{shortCode}")
    public ResponseEntity<Object> redirectToUrl(@PathVariable String shortCode, HttpServletRequest request) {
        String url = urlService.getUrlToRedirect(shortCode);
        URI uri = URI.create(url);
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add(HttpHeaders.LOCATION, url);
        return new ResponseEntity<>(uri, headers, HttpStatus.PERMANENT_REDIRECT);
    }
}

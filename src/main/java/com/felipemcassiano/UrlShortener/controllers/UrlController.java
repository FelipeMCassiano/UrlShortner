package com.felipemcassiano.UrlShortener.controllers;

import com.felipemcassiano.UrlShortener.dtos.CreateShortenedUrlDTO;
import com.felipemcassiano.UrlShortener.dtos.ResponseStatsUrlDTO;
import com.felipemcassiano.UrlShortener.dtos.ResponseUrlDTO;
import com.felipemcassiano.UrlShortener.dtos.UpdateUrlDTO;
import com.felipemcassiano.UrlShortener.services.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpClient;

@RestController
@RequestMapping("/shorten")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping()
    public ResponseEntity<ResponseUrlDTO> post(@RequestBody @Valid CreateShortenedUrlDTO createShortenedUrlDTO) {
        ResponseUrlDTO response = urlService.createShortenedUrl(createShortenedUrlDTO);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{shortCode}")
    public ResponseEntity<ResponseUrlDTO> get(@PathVariable String shortCode) {
        ResponseUrlDTO responseUrlDTO = urlService.getShortenedUrlByShortCode(shortCode);
        return new  ResponseEntity<>(responseUrlDTO, HttpStatus.OK);
    }
    @PutMapping("{shortCode}")
    public ResponseEntity<ResponseUrlDTO> update(@PathVariable String shortCode, @RequestBody @Valid UpdateUrlDTO updateUrlDTO) {
        ResponseUrlDTO responseUrlDTO = urlService.updateUrl(shortCode, updateUrlDTO);
        return new  ResponseEntity<>(responseUrlDTO, HttpStatus.OK);
    }

    @DeleteMapping("{shortCode}")
    public ResponseEntity<Void> delete(@PathVariable String shortCode) {
        urlService.deleteShortenedUrlByShortCode(shortCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{shortCode}/stats")
    public ResponseEntity<ResponseStatsUrlDTO> getUrlStats(@PathVariable String shortCode) {
        ResponseStatsUrlDTO responseStatsUrlDTO = urlService.getUrlStats(shortCode);
        return new  ResponseEntity<>(responseStatsUrlDTO, HttpStatus.OK);
    }

}

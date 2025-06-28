package com.felipemcassiano.UrlShortener.services;

import com.felipemcassiano.UrlShortener.Exceptions.NotFoundUrlException;
import com.felipemcassiano.UrlShortener.dtos.CreateShortenedUrlDTO;
import com.felipemcassiano.UrlShortener.dtos.ResponseStatsUrlDTO;
import com.felipemcassiano.UrlShortener.dtos.ResponseUrlDTO;
import com.felipemcassiano.UrlShortener.dtos.UpdateUrlDTO;
import com.felipemcassiano.UrlShortener.models.Url;
import com.felipemcassiano.UrlShortener.repositories.UrlRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public ResponseUrlDTO createShortenedUrl(CreateShortenedUrlDTO createShortenedUrlDTO) {
        Optional<Url> existentUrl = urlRepository.findByUrl(createShortenedUrlDTO.url());
        if (existentUrl.isPresent()) {
            return ResponseUrlDTO.fromEntity(existentUrl.get());
        }

        String shortCode = generateShortCode(createShortenedUrlDTO.url());
        Url url = new Url(createShortenedUrlDTO.url(), shortCode);
        Url createUrl = urlRepository.save(url);
        return ResponseUrlDTO.fromEntity(createUrl);
    }

    public ResponseUrlDTO getShortenedUrlByShortCode(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new NotFoundUrlException("Not found url with shortCode: " + shortCode));
        return ResponseUrlDTO.fromEntity(url);
    }
    public  ResponseUrlDTO updateUrl(String shortCode, UpdateUrlDTO updateUrlDTO) {
        Url url = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new NotFoundUrlException("Not found url with shortCode: " + shortCode));
        url.setUrl(updateUrlDTO.url());

        Url updateUrl = urlRepository.save(url);
        return ResponseUrlDTO.fromEntity(updateUrl);
    }
    public void deleteShortenedUrlByShortCode(String shortCode) {
        long rowsDeleted = urlRepository.deleteByShortCode(shortCode);
        if (rowsDeleted != 1){
            throw new NotFoundUrlException("Not found url with shortCode: " + shortCode);
        }
    }
    public ResponseStatsUrlDTO getUrlStats(String shortCode) {
       Url url = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new NotFoundUrlException("Not found url with shortCode: " + shortCode));
       return ResponseStatsUrlDTO.fromEntity(url);
    }
    public String getUrlToRedirect(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new NotFoundUrlException("Not found url with shortCode: " + shortCode));
        url.addAccessCount();
        urlRepository.save(url);

        return url.getUrl();
    }

    private String generateShortCode(String url){
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder
                .encodeToString(url.getBytes())
                .substring(0, 6)
                .toLowerCase();
    }
}

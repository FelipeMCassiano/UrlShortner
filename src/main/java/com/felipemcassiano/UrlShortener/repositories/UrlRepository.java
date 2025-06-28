package com.felipemcassiano.UrlShortener.repositories;

import com.felipemcassiano.UrlShortener.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UrlRepository extends MongoRepository<Url, Long> {
    Optional<Url> findByShortCode(String shortCode);
    Optional<Url> findByUrl(String url);

    long deleteByShortCode(String shortCode);
}

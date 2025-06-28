package com.felipemcassiano.UrlShortener.dtos;

import com.felipemcassiano.UrlShortener.models.Url;

import java.time.LocalDateTime;

public record ResponseUrlDTO(String id, String url, String shortCode, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public static ResponseUrlDTO fromEntity(Url url) {
        return new ResponseUrlDTO(url.getId(),  url.getUrl(), url.getShortCode(), url.getCreatedAt(), url.getUpdatedAt());
    }
}

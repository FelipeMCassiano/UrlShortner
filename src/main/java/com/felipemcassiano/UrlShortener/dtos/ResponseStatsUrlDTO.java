package com.felipemcassiano.UrlShortener.dtos;

import com.felipemcassiano.UrlShortener.models.Url;

import java.time.LocalDateTime;

public record ResponseStatsUrlDTO(String id, String url, String shortCode, LocalDateTime createdAt, LocalDateTime updatedAt, Long accessCount) {
    public static ResponseStatsUrlDTO fromEntity(Url url){
        return new ResponseStatsUrlDTO(url.getId(), url.getUrl(), url.getShortCode(), url.getCreatedAt(), url.getUpdatedAt(), url.getAccessCount());
    }
}

package com.felipemcassiano.UrlShortener.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateShortenedUrlDTO(@NotBlank String url) {
}

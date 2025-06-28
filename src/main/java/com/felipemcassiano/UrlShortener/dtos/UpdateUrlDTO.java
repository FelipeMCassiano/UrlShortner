package com.felipemcassiano.UrlShortener.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdateUrlDTO(@NotBlank String url) {
}

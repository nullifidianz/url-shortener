package com.nullifidianz.urlShortener.dto;

import java.time.Instant;

public record UrlResponse(
        Long id,
        String url,
        String shortCode,
        Instant createdAt,
        Instant updatedAt,
        Long accessCount
) {
}

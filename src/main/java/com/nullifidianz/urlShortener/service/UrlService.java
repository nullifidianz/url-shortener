package com.nullifidianz.urlShortener.service;

import com.nullifidianz.urlShortener.dto.UrlRequest;
import com.nullifidianz.urlShortener.dto.UrlResponse;
import com.nullifidianz.urlShortener.repository.UrlRepository;
import com.nullifidianz.urlShortener.entity.Url;
import org.springframework.stereotype.Service;

import java.time.Instant;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository repository;
    private final Random random = new Random();

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    private String generateShortCode() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb;
        do {
            sb = new StringBuilder();
            for (int i = 0; i < 6; i++)
                sb.append(chars.charAt(random.nextInt(chars.length())));
        } while (repository.findByShortCode(sb.toString()).isPresent());
        return sb.toString();
    }

    public UrlResponse create(UrlRequest req) {
        Url entity = Url.builder()
                .url(req.url())
                .shortCode(generateShortCode())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .accessCount(0L)
                .build();
        repository.save(entity);
        return map(entity);
    }

    public Optional<UrlResponse> get(String code) {
        return repository.findByShortCode(code).map(url -> {
            url.setAccessCount(url.getAccessCount() + 1);
            repository.save(url);
            return map(url);
        });
    }

    public Optional<UrlResponse> update(String code, UrlRequest req) {
        return repository.findByShortCode(code).map(url -> {
            url.setUrl(req.url());
            url.setUpdatedAt(Instant.now());
            repository.save(url);
            return map(url);
        });
    }

    public boolean delete(String code) {
        Optional<Url> found = repository.findByShortCode(code);
        found.ifPresent(repository::delete);
        return found.isPresent();
    }

    public Optional<UrlResponse> stats(String code) {
        return repository.findByShortCode(code).map(this::map);
    }

    private UrlResponse map(Url u) {
        return new UrlResponse(
                u.getId(),
                u.getUrl(),
                u.getShortCode(),
                u.getCreatedAt(),
                u.getUpdatedAt(),
                u.getAccessCount());
    }

    public Optional<UrlResponse> redirect(String code) {
        return repository.findByShortCode(code).map(url -> {
            url.setAccessCount(url.getAccessCount() + 1);
            repository.save(url);
            return map(url);
        });
    }

}
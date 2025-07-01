package com.nullifidianz.urlShortener.controller;

import com.nullifidianz.urlShortener.dto.UrlRequest;
import com.nullifidianz.urlShortener.dto.UrlResponse;
import com.nullifidianz.urlShortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shorten")
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUrl(@RequestBody UrlRequest req) {
        if (req.url() == null || req.url().isBlank())
            return ResponseEntity.badRequest().body("Invalid URL");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getUrl(@PathVariable String code) {
        Optional<UrlResponse> res = service.get(code);
        return res.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found"));
    }

    @PutMapping("/{code}")
    public ResponseEntity<?> updateUrl(@PathVariable String code, @RequestBody UrlRequest req) {
        if (req.url() == null || req.url().isBlank())
            return ResponseEntity.badRequest().body("Invalid URL");
        Optional<UrlResponse> res = service.update(code, req);
        return res.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found"));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String code) {
        return service.delete(code)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{code}/stats")
    public ResponseEntity<?> urlStats(@PathVariable String code) {
        Optional<UrlResponse> res = service.stats(code);
        return res.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found"));
    }

    @GetMapping("/{code}/redirect")
    public ResponseEntity<?> redirect(@PathVariable String code) {
        Optional<UrlResponse> res = service.redirect(code);
        return res.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found"));
    }
}
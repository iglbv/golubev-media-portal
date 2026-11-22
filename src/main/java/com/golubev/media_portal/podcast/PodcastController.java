package com.golubev.media_portal.podcast;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/podcasts")
public class PodcastController {
    private final PodcastService service;

    public PodcastController(PodcastService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PodcastResponse create(@Valid @RequestBody PodcastRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<PodcastResponse> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public PodcastResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public PodcastResponse update(@PathVariable Long id, @Valid @RequestBody PodcastRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

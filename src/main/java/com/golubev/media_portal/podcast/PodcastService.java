package com.golubev.media_portal.podcast;

import com.golubev.media_portal.common.NotFoundException;
import com.golubev.media_portal.common.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PodcastService {
    private final PodcastRepository repository;

    public PodcastService(PodcastRepository repository) {
        this.repository = repository;
    }

    public PodcastResponse create(PodcastRequest request) {
        validateEpisodeNumbers(request.episodes());
        Podcast podcast = new Podcast();
        podcast.setTitle(request.title());
        addEpisodes(podcast, request.episodes());
        return PodcastResponse.from(repository.save(podcast));
    }

    @Transactional(readOnly = true)
    public PodcastResponse get(Long id) {
        return PodcastResponse.from(find(id));
    }

    @Transactional(readOnly = true)
    public List<PodcastResponse> list() {
        return repository.findAll().stream().map(PodcastResponse::from).toList();
    }

    public PodcastResponse update(Long id, PodcastRequest request) {
        validateEpisodeNumbers(request.episodes());
        Podcast podcast = find(id);
        podcast.setTitle(request.title());
        podcast.replaceEpisodes(request.episodes().stream().map(this::toEpisode).toList());
        return PodcastResponse.from(repository.save(podcast));
    }

    public void delete(Long id) {
        repository.delete(find(id));
    }

    private Podcast find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Podcast not found: " + id));
    }

    private void addEpisodes(Podcast podcast, List<EpisodeRequest> requests) {
        for (EpisodeRequest request : requests) podcast.addEpisode(toEpisode(request));
    }

    private PodcastEpisode toEpisode(EpisodeRequest request) {
        PodcastEpisode episode = new PodcastEpisode();
        episode.setEpisodeNumber(request.episodeNumber());
        episode.setTitle(request.title());
        episode.setAudioUrl(request.audioUrl());
        episode.setDurationSeconds(request.durationSeconds());
        return episode;
    }

    private void validateEpisodeNumbers(List<EpisodeRequest> episodes) {
        Set<Integer> numbers = new HashSet<>();
        for (EpisodeRequest episode : episodes) {
            if (!numbers.add(episode.episodeNumber())) {
                throw new ValidationException("Episode numbers must be unique");
            }
        }
    }
}

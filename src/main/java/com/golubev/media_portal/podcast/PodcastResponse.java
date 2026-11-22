package com.golubev.media_portal.podcast;

import java.util.List;

public record PodcastResponse(Long id, String title, List<EpisodeResponse> episodes) {
    public static PodcastResponse from(Podcast podcast) {
        return new PodcastResponse(podcast.getId(), podcast.getTitle(),
                podcast.getEpisodes().stream().map(EpisodeResponse::from).toList());
    }
}

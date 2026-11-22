package com.golubev.media_portal.podcast;

public record EpisodeResponse(Long id, int episodeNumber, String title, String audioUrl, int durationSeconds) {
    public static EpisodeResponse from(PodcastEpisode episode) {
        return new EpisodeResponse(episode.getId(), episode.getEpisodeNumber(), episode.getTitle(),
                episode.getAudioUrl(), episode.getDurationSeconds());
    }
}

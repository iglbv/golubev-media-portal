package com.golubev.media_portal.podcast;

import jakarta.validation.constraints.*;

public record EpisodeRequest(
        @Positive int episodeNumber,
        @NotBlank @Size(max = 255) String title,
        @NotBlank @Size(max = 2048) String audioUrl,
        @Positive int durationSeconds
) {}

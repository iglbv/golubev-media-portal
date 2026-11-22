package com.golubev.media_portal.podcast;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public record PodcastRequest(
        @NotBlank @Size(max = 255) String title,
        @Valid @Size(min = 1) List<EpisodeRequest> episodes
) {}

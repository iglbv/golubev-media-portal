package com.golubev.media_portal.video;

import jakarta.validation.constraints.*;

public record VideoRequest(
        @NotBlank @Size(max = 255) String title,
        @NotBlank @Size(max = 2048) String url,
        @Positive int durationSeconds
) {}

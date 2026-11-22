package com.golubev.media_portal.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record ArticleRequest(
        @NotBlank @Size(max = 255) String title,
        @NotBlank String text,
        @NotBlank @Size(max = 255) String author,
        @NotNull LocalDateTime publishedAt
) {}

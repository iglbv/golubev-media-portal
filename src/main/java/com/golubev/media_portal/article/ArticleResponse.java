package com.golubev.media_portal.article;

import java.time.LocalDateTime;

public record ArticleResponse(Long id, String title, String text, String author, LocalDateTime publishedAt) {
    public static ArticleResponse from(Article article) {
        return new ArticleResponse(article.getId(), article.getTitle(), article.getText(),
                article.getAuthor(), article.getPublishedAt());
    }
}

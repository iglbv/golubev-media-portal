package com.golubev.media_portal.article;

import com.golubev.media_portal.common.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @Mock
    private ArticleRepository repository;
    @InjectMocks
    private ArticleService service;

    @Test
    void createAndGetArticle() {
        ArticleRequest request = new ArticleRequest("Title", "Text", "Author", LocalDateTime.now());
        Article saved = new Article();
        saved.setId(1L);
        saved.setTitle(request.title());
        saved.setText(request.text());
        saved.setAuthor(request.author());
        saved.setPublishedAt(request.publishedAt());
        when(repository.save(any(Article.class))).thenReturn(saved);

        ArticleResponse created = service.create(request);

        assertEquals(1L, created.id());
        assertEquals("Title", created.title());
    }

    @Test
    void missingArticleThrowsNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.get(99L));
    }
}

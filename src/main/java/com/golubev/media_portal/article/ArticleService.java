package com.golubev.media_portal.article;

import com.golubev.media_portal.common.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ArticleService {
    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public ArticleResponse create(ArticleRequest request) {
        Article article = new Article();
        apply(article, request);
        return ArticleResponse.from(repository.save(article));
    }

    @Transactional(readOnly = true)
    public ArticleResponse get(Long id) {
        return ArticleResponse.from(find(id));
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> list() {
        return repository.findAll().stream().map(ArticleResponse::from).toList();
    }

    public ArticleResponse update(Long id, ArticleRequest request) {
        Article article = find(id);
        apply(article, request);
        return ArticleResponse.from(repository.save(article));
    }

    public void delete(Long id) {
        repository.delete(find(id));
    }

    private Article find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Article not found: " + id));
    }

    private void apply(Article article, ArticleRequest request) {
        article.setTitle(request.title());
        article.setText(request.text());
        article.setAuthor(request.author());
        article.setPublishedAt(request.publishedAt());
    }
}

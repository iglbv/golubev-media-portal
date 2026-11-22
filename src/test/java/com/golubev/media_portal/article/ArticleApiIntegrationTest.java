package com.golubev.media_portal.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ArticleApiIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ArticleRepository repository;

    @BeforeEach
    void clear() {
        repository.deleteAll();
    }

    @Test
    void createGetUpdateDeleteArticle() throws Exception {
        ArticleRequest request = new ArticleRequest("Test", "Article text", "Author",
                java.time.LocalDateTime.of(2026, 1, 1, 12, 0));

        String createdJson = mockMvc.perform(post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test"))
                .andReturn().getResponse().getContentAsString();

        ArticleResponse created = objectMapper.readValue(createdJson, ArticleResponse.class);

        mockMvc.perform(get("/api/articles/{id}", created.id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("Author"));

        ArticleRequest updated = new ArticleRequest("Updated", "Updated text", "Author",
                request.publishedAt());
        mockMvc.perform(put("/api/articles/{id}", created.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated"));

        mockMvc.perform(delete("/api/articles/{id}", created.id()))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/articles/{id}", created.id()))
                .andExpect(status().isNotFound());
    }

    @Test
    void rejectsInvalidArticle() throws Exception {
        String invalid = """
                {"title":"","text":"","author":"","publishedAt":null}
                """;
        mockMvc.perform(post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalid))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }
}

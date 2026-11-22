package com.golubev.media_portal.podcast;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "podcasts")
public class Podcast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @OneToMany(mappedBy = "podcast", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("episodeNumber ASC")
    private List<PodcastEpisode> episodes = new ArrayList<>();

    public void addEpisode(PodcastEpisode episode) {
        episodes.add(episode);
        episode.setPodcast(this);
    }

    public void replaceEpisodes(List<PodcastEpisode> episodes) {
        this.episodes.clear();
        for (PodcastEpisode episode : episodes) addEpisode(episode);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<PodcastEpisode> getEpisodes() { return episodes; }
}

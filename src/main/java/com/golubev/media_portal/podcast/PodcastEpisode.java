package com.golubev.media_portal.podcast;

import jakarta.persistence.*;

@Entity
@Table(name = "podcast_episodes")
public class PodcastEpisode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "podcast_id", nullable = false)
    private Podcast podcast;
    @Column(name = "episode_number", nullable = false)
    private int episodeNumber;
    @Column(nullable = false)
    private String title;
    @Column(name = "audio_url", nullable = false)
    private String audioUrl;
    @Column(name = "duration_seconds", nullable = false)
    private int durationSeconds;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Podcast getPodcast() { return podcast; }
    public void setPodcast(Podcast podcast) { this.podcast = podcast; }
    public int getEpisodeNumber() { return episodeNumber; }
    public void setEpisodeNumber(int episodeNumber) { this.episodeNumber = episodeNumber; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }
    public int getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }
}

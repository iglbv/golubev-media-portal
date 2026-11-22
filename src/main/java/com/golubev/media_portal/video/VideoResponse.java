package com.golubev.media_portal.video;

public record VideoResponse(Long id, String title, String url, int durationSeconds) {
    public static VideoResponse from(Video video) {
        return new VideoResponse(video.getId(), video.getTitle(), video.getUrl(), video.getDurationSeconds());
    }
}

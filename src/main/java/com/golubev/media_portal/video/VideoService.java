package com.golubev.media_portal.video;

import com.golubev.media_portal.common.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class VideoService {
    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public VideoResponse create(VideoRequest request) {
        Video video = new Video();
        apply(video, request);
        return VideoResponse.from(repository.save(video));
    }

    @Transactional(readOnly = true)
    public VideoResponse get(Long id) {
        return VideoResponse.from(find(id));
    }

    @Transactional(readOnly = true)
    public List<VideoResponse> list() {
        return repository.findAll().stream().map(VideoResponse::from).toList();
    }

    public VideoResponse update(Long id, VideoRequest request) {
        Video video = find(id);
        apply(video, request);
        return VideoResponse.from(repository.save(video));
    }

    public void delete(Long id) {
        repository.delete(find(id));
    }

    private Video find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Video not found: " + id));
    }

    private void apply(Video video, VideoRequest request) {
        video.setTitle(request.title());
        video.setUrl(request.url());
        video.setDurationSeconds(request.durationSeconds());
    }
}

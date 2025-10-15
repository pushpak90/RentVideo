package com.example.demo.Service;

import java.util.List;

import com.example.demo.Dto.VideoDto;

public interface VideoService {
    public VideoDto saveVideo(VideoDto videoDto);
    public VideoDto updateVideo(long id, VideoDto videoDto);
    public boolean deleteVideo(long id);
    public List<VideoDto> getAllVideo();
}

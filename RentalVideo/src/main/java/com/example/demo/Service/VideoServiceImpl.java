package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.VideoDto;
import com.example.demo.Entity.Video;
import com.example.demo.Repository.VideoRepository;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    ModelMapper modelMapper = new ModelMapper();

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public VideoDto saveVideo(VideoDto videoDto) {
        Video video = modelMapper.map(videoDto, Video.class);
        Video saved = videoRepository.save(video);
        return modelMapper.map(saved, VideoDto.class);
    }

    @Override
    public VideoDto updateVideo(long id, VideoDto videoDto) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found by ID : " + id));
        video.setGenre(videoDto.getGenre());
        video.setDirector(videoDto.getDirector());
        video.setGenre(videoDto.getGenre());
        video.setStatus(videoDto.getStatus());
        return modelMapper.map(videoRepository.save(video), VideoDto.class);
    }

    @Override
    public boolean deleteVideo(long id) {
        if (videoRepository.existsById(id)) {
            videoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<VideoDto> getAllVideo() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream().map(video -> modelMapper.map(video, VideoDto.class))
                .collect(Collectors.toList());

    }

}

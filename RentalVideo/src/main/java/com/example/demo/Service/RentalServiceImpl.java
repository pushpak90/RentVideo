package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.RentalDto;
import com.example.demo.Dto.VideoDto;
import com.example.demo.Entity.Rental;
import com.example.demo.Entity.Video;
import com.example.demo.Repository.RentalRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.VideoRepository;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    ModelMapper modelMapper = new ModelMapper();

    public RentalServiceImpl(RentalRepository rentalRepository,
            UserRepository userRepository,
            VideoRepository videoRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
    }

    @Override
    public RentalDto rentVideo(RentalDto rentalDto) {
        if (userRepository.existsById(rentalDto.getUserId())
                && videoRepository.existsById(rentalDto.getVideoId())) {
            Rental rental = modelMapper.map(rentalDto, Rental.class);
            Rental saved = rentalRepository.save(rental);
            Video video = videoRepository.findById(rentalDto.getVideoId())
                    .orElseThrow(() -> new RuntimeException("Video not found by ID : " + rentalDto.getVideoId()));
            video.setStatus(0);
            videoRepository.save(video);
            return modelMapper.map(saved, RentalDto.class);
        }
        return null;
    }

    @Override
    public List<VideoDto> getAllVideo() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream()
                .filter(video -> video.getStatus() == 1)
                .map(video -> modelMapper.map(video, VideoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VideoDto> getAllRentedVideo(long id) {
        List<Rental> rentals = rentalRepository.findByUserId(id);

        return rentals.stream()
                .map(video -> modelMapper.map(video, VideoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean returnVideo(long id) {

        if(rentalRepository.existsById(id)){
            Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
            Video video = videoRepository.findById(rental.getVideoId()).orElseThrow(() -> new RuntimeException("Not Found"));
            video.setStatus(1);
            videoRepository.save(video);
            rentalRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

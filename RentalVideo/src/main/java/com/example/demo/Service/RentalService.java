package com.example.demo.Service;

import java.util.List;

import com.example.demo.Dto.RentalDto;
import com.example.demo.Dto.VideoDto;

public interface RentalService {
    public RentalDto rentVideo(RentalDto rentalDto);
    public List<VideoDto> getAllVideo();
    public List<VideoDto> getAllRentedVideo(long id);
    public boolean returnVideo(long id);
}

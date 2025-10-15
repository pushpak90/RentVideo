package com.example.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.VideoDto;
import com.example.demo.Service.VideoService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final VideoService videoService;

    public AdminController(VideoService videoService){
        this.videoService = videoService;
    }

    @PostMapping
    public VideoDto saveVideo(@RequestBody VideoDto videoDto){
        return videoService.saveVideo(videoDto);
    }

    @PutMapping("/{id}")
    public VideoDto updateVideo(@PathVariable("id") long id, @RequestBody VideoDto videoDto){
        return videoService.updateVideo(id, videoDto);
    }
    
    @DeleteMapping("/{id}")
    public String deleteVideo(@PathVariable("id") long id){
        if(videoService.deleteVideo(id)){
            return "Record Delete";
        }
        else{
            return "Record Not Found By ID : " + id;
        }
    }
    @GetMapping
    public List<VideoDto> getAllVideos(){
        return videoService.getAllVideo();
    }
}

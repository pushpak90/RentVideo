package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.RentalDto;
import com.example.demo.Dto.VideoDto;
import com.example.demo.Service.RentalService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final RentalService rentalService;
    public CustomerController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @PostMapping
    public RentalDto rentVideo(@RequestBody RentalDto rentalDto){
        return rentalService.rentVideo(rentalDto);
    }

    @GetMapping
    public List<VideoDto> getVideo(){
        return rentalService.getAllVideo();
    }

    @GetMapping("/{userid}")
    public List<VideoDto> getRentedVideoByUser(@PathVariable("userid") long id){
        return rentalService.getAllRentedVideo(id);
    }

    @DeleteMapping("/return/{id}")
    public String returnVideo(@PathVariable("id") long id){
        if(rentalService.returnVideo(id)){
            return "Video Return";
        }
        return "Rented Video Not found at ID : " + id;
    }

}

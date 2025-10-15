package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{
    
}

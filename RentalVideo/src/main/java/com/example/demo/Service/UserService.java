package com.example.demo.Service;

import java.util.Optional;

import com.example.demo.Dto.UserDto;

public interface UserService {
    public UserDto addUser(UserDto userDto);
    Optional<UserDto> findByEmail(String email);
    Optional<UserDto> login(String email, String password);
}

package com.example.demo.Service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto addUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto, User.class);
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserDto.class);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(users -> modelMapper.map(users, UserDto.class));
    }

    @Override
    public Optional<UserDto> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email)
               .filter(u -> passwordEncoder.matches(password, u.getPassword()));
        return user.map(u -> modelMapper.map(u, UserDto.class));
    }
  
}

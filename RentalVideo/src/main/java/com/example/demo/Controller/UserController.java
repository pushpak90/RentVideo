package com.example.demo.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.UserDto;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDto addUser(@RequestBody UserDto userDto) {
        if(userDto.getRole() == "" || userDto.getRole().isEmpty()){
            userDto.setRole("Customer");
        }
        return userService.addUser(userDto);
    }

    @GetMapping
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        try {
            Optional<UserDto> userDto = userService.findByEmail(email);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/userlogin")
    public Map<String, String> login(@RequestParam String email, @RequestParam String password){
        return userService.login(email, password).
        map(u -> Map.of("Message","Login Done",
                        "Email : ",u.getEmail(),
                        "Password",u.getPassword(),
                        "Role : ", u.getRole()))
        .orElse(Map.of("Message : ","Login Fail"));
    }

}

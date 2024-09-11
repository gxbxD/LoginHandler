package online.liberumscientia.loginhandler.controller;

import online.liberumscientia.loginhandler.dto.LoginResponse;
import online.liberumscientia.loginhandler.dto.UserDto;
import online.liberumscientia.loginhandler.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/home")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        try {
            String token = authService.authenticate(userDto.getEmail(), userDto.getPassword());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
    
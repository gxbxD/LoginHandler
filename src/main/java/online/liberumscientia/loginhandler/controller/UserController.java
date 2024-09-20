package online.liberumscientia.loginhandler.controller;

import online.liberumscientia.loginhandler.service.AuthService;
import online.liberumscientia.loginhandler.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

        @Autowired
        private AuthService authService;

        @PostMapping("/api/register")
        public ResponseEntity<?>registerUser(@RequestBody UserDto userDto) {
            try {
                authService.registerUser(userDto.getEmail(), userDto.getPassword());
                return ResponseEntity.ok("Usuário Registrado com sucesso!");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Erro:" + e.getMessage());
            }
        }
}

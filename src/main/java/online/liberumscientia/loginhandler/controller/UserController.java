package online.liberumscientia.loginhandler.controller;

@RestController
public class UserController {

        @Autowired
        private AuthService authService;

        @PostMapping("/api/register")
        public ResponseEntity<?>registerUser(@Valid @RequestBody UserDto userDto) {
            try {
                authService.registerUser(userDto.getEmail(), userDto.getPassword());
                return ResponseEntity.ok("Usuário Registrado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erro:" + e.getMessage());
            }
        }
}

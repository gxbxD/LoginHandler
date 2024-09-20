package online.liberumscientia.loginhandler.service;

import online.liberumscientia.loginhandler.model.User;
import online.liberumscientia.loginhandler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }
        return jwtTokenProvider.createToken(email);
    }

    public User registerUser(String email, String password) {
    // Verifica se o email já está registrado
    if (userRepository.findByEmail(email).isPresent()) {
        throw new IllegalArgumentException("Este email já está em uso.");
    }

    // Caso o email não esteja registrado, cria um novo usuário
    User user = new User();
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));

    return userRepository.save(user);
}

    public User loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(null);
    }
}

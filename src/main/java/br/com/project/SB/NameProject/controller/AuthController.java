package br.com.project.SB.NameProject.controller;

import br.com.project.SB.NameProject.infra.security.TokenService;
import br.com.project.SB.NameProject.model.user.AuthenticationDto;
import br.com.project.SB.NameProject.model.user.RegisterDto;
import br.com.project.SB.NameProject.model.user.ResponseDto;
import br.com.project.SB.NameProject.model.user.User;
import br.com.project.SB.NameProject.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.username(), authenticationDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new ResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto) {
        if (this.userRepository.findByUsername(registerDto.username()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        User newUser = new User(registerDto.username(), encryptedPassword, registerDto.role());
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}

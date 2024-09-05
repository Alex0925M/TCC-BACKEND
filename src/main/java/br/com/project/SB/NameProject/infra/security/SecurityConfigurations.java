package br.com.project.SB.NameProject.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    public static final List<String> WHITELIST = Arrays.asList(
            "/actuator/*",
            "/configuration/security",
            "/configuration/ui",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-resources/configuration/security",
            "/swagger-resources/configuration/ui",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/v3/api-docs/**",
            "/webjars/**",
            "/webjars/swagger-ui/**",
            "/error",
            "/auth/login",
            "/auth/resgister"
    );

    public static final List<String> AUTHENTICATED = Arrays.asList(
            "/employees",
            "/employees/**",
            "/clients",
            "/clients/**",
            "/authenticated/clients",
            "/authenticated/company",
            "/company/**",
            "/infos-companies",
            "/infos/**"
    );

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITELIST.toArray(String[]::new)).permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/clients/**").authenticated()  // Permite acesso autenticado para /clients/** endpoints
                        .requestMatchers("/employees/**").authenticated()
                        .requestMatchers("authenticated/**").authenticated()
                        .requestMatchers("authenticated/company").authenticated()
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/company/**").authenticated()
                        .requestMatchers("/infos-companies").authenticated()
                        .requestMatchers("/infos-**/").authenticated()
                        .requestMatchers("/authenticated/clients").authenticated()
                        .requestMatchers("/authenticated/company").authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

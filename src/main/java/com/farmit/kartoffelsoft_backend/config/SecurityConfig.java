package com.farmit.kartoffelsoft_backend.config;

import com.farmit.kartoffelsoft_backend.repository.MitarbeiterRepository;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Data // Lombok-Annotation: erzeugt automatisch Getter, Setter, toString, equals, hashCode und RequiredArgsConstructo
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MitarbeiterRepository mitarbeiterRepository;

    public SecurityConfig(MitarbeiterRepository mitarbeiterRepository) {
        this.mitarbeiterRepository = mitarbeiterRepository;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            return mitarbeiterRepository.findByUsername(username)
                    .map(mitarbeiter -> User
                            .withUsername(mitarbeiter.getUsername())
                            .password(mitarbeiter.getPassword())
                            .roles(mitarbeiter.getRole().name())
                            .build())
                    .orElseThrow(() -> new UsernameNotFoundException("Benutzer mit dem Benutzernamen " + username + " nicht gefunden."));
        };
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deaktiviert CSRF, da unser Frontend kein Formular-basiertes CSRF verwendet
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/h2-console/**").permitAll()  // Erlaubt den Zugriff auf die H2-Konsole
                        .requestMatchers("/api/mitarbeiter/login").permitAll() // Erlaubt den Zugriff auf Ihren eigenen Login-Endpunkt
                        .requestMatchers("/api/mitarbeiter/register").permitAll() // Erlaubt die Registrierung der Mitarbeiter
                        .requestMatchers("/api/mitarbeiter").permitAll()
                        .requestMatchers("/api/abteilung/register").permitAll()
                        .requestMatchers("/api/abteilung").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Setzt die Session-Politik auf stateless, da wir token-basiert arbeiten werden;

        return http.build();
    }
}
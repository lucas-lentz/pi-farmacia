package com.farmacia.farmacia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf
                    .ignoringRequestMatchers("/departamentos/**", "/marcas/**",
                    "/categorias/**", "/produtos/**") // Ignora CSRF para o endpoint específico
                )
                .cors(Customizer.withDefaults()) // Ativa CORS com configurações do WebConfig

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**", "/CSS/**", 
                        "/Javascript/**", "/Assets/**", "/login").permitAll()
                        .anyRequest().authenticated() // Exige autenticação para qualquer outra rota
                )

                .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/login/index", true)
                    .permitAll()
                )
                .rememberMe(Customizer.withDefaults());

        return http.build();
    }
}




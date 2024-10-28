package com.farmacia.farmacia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            //.csrf(csrf -> csrf.disable()) // Desabilita CSRF temporariamente
            .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // Configura o repositório de token CSRF
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/images/**", "/html/**", "login", "home").permitAll() /* CONFIRMAR SE O BACKEND FUNCIONARÁ SEM COLOCAR AQUI */
                .anyRequest().authenticated() // Demais páginas necessitam de autenticação
            )

            .formLogin(formLogin -> formLogin
                .loginPage("/login") // Especifica a página de login
                .defaultSuccessUrl("/login/home", true) // Redireciona para /home após login bem sucedido
                .permitAll()
            )
            .rememberMe(Customizer.withDefaults());

        return http.build();
    }
}

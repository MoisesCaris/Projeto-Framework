package com.projeto.projetofarmaciatcsframework.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produtos").authenticated()
                        .requestMatchers(HttpMethod.GET, "/setores").authenticated()
                        .requestMatchers(HttpMethod.GET, "/funcionarios").authenticated()
                        .requestMatchers(HttpMethod.GET, "/compra/listar").authenticated()
                        .requestMatchers(HttpMethod.GET, "/transportadora/listar").authenticated()
                        .requestMatchers(HttpMethod.POST, "/setores/registrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/produtos/registro").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.POST, "/farmacia/registrar").hasRole("GERENTE")
                        .requestMatchers(HttpMethod.POST, "/funcionario/adicionar").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/transportadoras/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/funcionario/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/setor/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/venda/vender").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.POST, "/compra/comprar").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.POST, "/compra/adicionar").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.POST, "/venda/adicionar").hasRole("FUNCIONARIO")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*"));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

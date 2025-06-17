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
                // 1. HABILITA a configuração de CORS definida no Bean abaixo
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Suas regras existentes, sem alterações:
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produtos").authenticated()
                        .requestMatchers(HttpMethod.GET, "/setores").authenticated()
                        .requestMatchers(HttpMethod.GET, "/funcionarios").authenticated()
                        .requestMatchers(HttpMethod.POST, "/set/registrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/setores/registrar").hasRole("GERENTE")
                        .requestMatchers(HttpMethod.POST, "/produtos/registro").hasRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.POST, "/farmacia/registrar").hasRole("GERENTE")
                        .requestMatchers(HttpMethod.POST, "/funcionario/adicionar").hasRole("GERENTE")
                        .requestMatchers(HttpMethod.DELETE, "/transportadoras/{id}").hasRole("GERENTE")
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

    // 2. BEAN que define as regras de CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Permite requisições de qualquer origem. Para produção, é mais seguro especificar a URL do seu frontend.
        configuration.setAllowedOrigins(List.of("*"));

        // Libera os métodos HTTP que seu frontend usará
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Permite todos os cabeçalhos nas requisições (incluindo 'Authorization')
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica esta configuração para todos os endpoints da API
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

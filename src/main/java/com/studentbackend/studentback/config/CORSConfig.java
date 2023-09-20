package com.studentbackend.studentback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORSConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Configura los permisos CORS para tu dominio o '*'
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*"); // Puedes restringir a métodos específicos como GET, POST, etc.
        config.addAllowedHeader("*"); // Puedes especificar encabezados permitidos aquí.

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

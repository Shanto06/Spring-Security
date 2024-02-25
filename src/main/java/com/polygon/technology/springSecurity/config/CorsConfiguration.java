package com.polygon.technology.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for Cross-Origin Resource Sharing (CORS) settings in the application.
 */
@Configuration
public class CorsConfiguration {

    /**
     * Bean for providing a custom WebMvcConfigurer implementation to configure CORS.
     *
     * @return WebMvcConfigurer instance.
     */
    @Bean
    public WebMvcConfigurer customCorsConfiguration() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Allow all origins, methods, and headers for simplicity.
                // In a production environment, consider restricting access based on your requirements.
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}

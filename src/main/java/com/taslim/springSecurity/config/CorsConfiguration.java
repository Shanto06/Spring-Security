package com.taslim.springSecurity.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

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
                // Configuring CORS to allow requests from all origins and methods,
                // with headers to be handled by the browser.
                // In a production environment, consider restricting access based on your requirements.
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}

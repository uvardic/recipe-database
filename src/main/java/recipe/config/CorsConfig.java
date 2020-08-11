package recipe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer cors() {
        return new WebMvcConfigurerImpl();
    }

    private static class WebMvcConfigurerImpl implements WebMvcConfigurer {

        private static final String[] ALLOWED_ORIGINS = new String[] { "http://localhost:8080" };

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**").allowedOrigins(ALLOWED_ORIGINS);
        }

    }

}

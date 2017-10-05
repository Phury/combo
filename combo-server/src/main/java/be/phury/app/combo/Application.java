package be.phury.app.combo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Phury
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public List<Combo> bootstrap() {
        return Arrays.asList(
                new Combo(){{
                    setName("draw 10 minutes");
                    setHits(42);
                    setHitsLimit(31);
                }},
                new Combo(){{
                    setName("drink 5 glasses of water a day");
                    setHits(16);
                    setHitsLimit(3);
                }},
                new Combo(){{
                    setName("do 100 suburi");
                    setHits(2);
                    setHitsLimit(31);
                }},
                new Combo(){{
                    setName("go to fitness");
                    setHits(10);
                    setHitsLimit(7);
                }}
        );
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/combo/**").allowedOrigins("http://localhost:8000");
            }
        };
    }

    /**
     * Heroku uses environment property PORT to set the port of the application.
     * Check if that variable is set, otherwise default to spring's configuration
     */
    @Configuration
    public class ApplicationCustomizer {
        @Bean
        public EmbeddedServletContainerCustomizer containerCustomizer() {
            return (container -> {
                if (System.getenv("PORT") != null) container.setPort(Integer.parseInt(System.getenv("PORT")));
            });
        }
    }
}

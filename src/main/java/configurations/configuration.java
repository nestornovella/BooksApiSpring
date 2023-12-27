package configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class configuration {
    @Bean
    public WebClient.Builder webBuilder(){
        return WebClient.builder();
    }
}









package com.example.springboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        // TODO: spring security로 인증기능을 추가할 때에는 코드를 바꿔야 한다.
        return () -> Optional.of("unnamed");
    }
}

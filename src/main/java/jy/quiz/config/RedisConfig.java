package jy.quiz.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jy.quiz.dto.QuizCommonResultResponseDto;
import jy.quiz.dto.QuizStatusDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, QuizStatusDto> quizStatusTemplate(RedisConnectionFactory connectionFactory) {
        return createTemplate(connectionFactory, QuizStatusDto.class);
    }

    @Bean
    public RedisTemplate<String, QuizCommonResultResponseDto> quizResultTemplate(RedisConnectionFactory connectionFactory) {
        return createTemplate(connectionFactory, QuizCommonResultResponseDto.class);
    }

    /**
     * Serializer: Java 객체 → JSON 직렬화
     * afterPropertiesSet(): Property 검증 역할 → 없으면 Bean 등록 실패로 인해 애플리케이션 실행 불가
     */
    private <T> RedisTemplate<String, T> createTemplate(RedisConnectionFactory connectionFactory, Class<T> type) {
        RedisTemplate<String, T> template = new RedisTemplate<>();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Jackson2JsonRedisSerializer<T> serializer = new Jackson2JsonRedisSerializer<>(type);

        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.afterPropertiesSet();

        return template;
    }
}

package jy.quiz.service;

import jy.quiz.dto.QuizCommonResultResponseDto;
import jy.quiz.enums.QuizStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RedisService {

    private static final String QUIZ_KEY_PREFIX = "quiz:";

    private final RedisTemplate<String, Object> redisTemplate;

    public void saveStatus(UUID uuid, QuizStatus status) {
        redisTemplate.opsForValue().set(
                QUIZ_KEY_PREFIX + uuid.toString(),
                status,
                Duration.ofHours(1)
        );
    }

    public void saveResult(UUID uuid, QuizCommonResultResponseDto resultResponseDto) {
        redisTemplate.opsForValue().set(
                QUIZ_KEY_PREFIX + uuid.toString(),
                resultResponseDto,
                Duration.ofHours(1)
        );
    }
}

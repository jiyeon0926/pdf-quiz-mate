package jy.quiz.service;

import jy.quiz.dto.QuizCommonResultResponseDto;
import jy.quiz.dto.QuizStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RedisService {

    private static final String QUIZ_STATUS_KEY_PREFIX = "quiz:status:";
    private static final String QUIZ_RESULT_KEY_PREFIX = "quiz:result:";

    private final RedisTemplate<String, QuizStatusDto> quizStatusTemplate;
    private final RedisTemplate<String, QuizCommonResultResponseDto> quizResultTemplate;

    public void saveStatus(UUID uuid, QuizStatusDto statusDto) {
        String key = QUIZ_STATUS_KEY_PREFIX + uuid.toString();
        quizStatusTemplate.opsForValue().set(
                key,
                statusDto,
                Duration.ofHours(1)
        );
    }

    public void saveResult(UUID uuid, QuizCommonResultResponseDto resultResponseDto) {
        String key = QUIZ_RESULT_KEY_PREFIX + uuid.toString();
        quizResultTemplate.opsForValue().set(
                key,
                resultResponseDto,
                Duration.ofHours(1)
        );
    }

    public void deleteStatus(UUID uuid) {
        String key = QUIZ_STATUS_KEY_PREFIX + uuid.toString();
        quizStatusTemplate.delete(key);
    }

    public QuizStatusDto getStatus(UUID uuid) {
        String key = QUIZ_STATUS_KEY_PREFIX + uuid.toString();

        return quizStatusTemplate.opsForValue().get(key);
    }
}

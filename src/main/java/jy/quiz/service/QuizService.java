package jy.quiz.service;

import jy.quiz.dto.QuizStatusDto;
import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final RedisService redisService;
    private final AsyncService asyncService;

    public UUID generateQuiz(QuestionType type, int count, MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        redisService.saveStatus(uuid, QuizStatusDto.of(QuizStatus.PROCESSING));
        asyncService.generateQuiz(uuid, type, count, file);

        return uuid;
    }
}

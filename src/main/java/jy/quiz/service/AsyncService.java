package jy.quiz.service;

import jy.quiz.dto.*;
import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import jy.quiz.exception.ServerCustomException;
import jy.quiz.service.pdf.PdfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AsyncService {

    private final PdfService pdfService;
    private final AiService aiService;
    private final RedisService redisService;

    @Async
    public void generateQuiz(UUID uuid, QuestionType type, int count, MultipartFile file) {
        try {
            String text = pdfService.extractTextFromPdf(file);
            QuizCommonAiResponseDto aiResponseDto = aiService.generateQuiz(
                    type,
                    count,
                    text
            );

            QuizCommonResultResponseDto resultResponseDto = convert(aiResponseDto);

            log.info("문제 생성 성공: {}", uuid);
            redisService.saveResult(uuid, resultResponseDto);
            redisService.deleteStatus(uuid);
        } catch (Exception e) {
            log.error("문제 생성 실패: {}", uuid);
            redisService.saveStatus(uuid, QuizStatusDto.of(QuizStatus.FAILED));
            throw new ServerCustomException("문제 생성 실패: " + e.getMessage(), e);
        }
    }

    private QuizCommonResultResponseDto convert(QuizCommonAiResponseDto aiResponseDto) {
        return switch (aiResponseDto) {
            case MultipleAiResponseDto dto -> MultipleResultResponseDto.completed(dto);
            case ShortAndOxAiResponseDto dto -> ShortAndOxResultResponseDto.completed(dto);
            default -> throw new IllegalArgumentException("지원하지 않는 문제 유형입니다.");
        };
    }
}

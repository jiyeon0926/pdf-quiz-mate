package jy.quiz.service;

import jy.quiz.dto.QuizCommonResultResponseDto;
import jy.quiz.dto.QuizStatusDto;
import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import jy.quiz.exception.NotFoundException;
import jy.quiz.service.pdf.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final RedisService redisService;
    private final AsyncService asyncService;
    private final PdfService pdfService;

    public UUID generateQuiz(QuestionType type, int count, MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        redisService.saveStatus(uuid, QuizStatusDto.of(QuizStatus.PROCESSING));
        asyncService.generateQuiz(uuid, type, count, file);

        return uuid;
    }

    public QuizStatusDto getStatus(UUID uuid) {
        QuizStatusDto status = redisService.getStatus(uuid);
        if (status != null) {
            return status;
        }

        QuizCommonResultResponseDto result = redisService.getResult(uuid);
        if (result != null) {
            QuizStatus resultStatus = result.getStatus();

            return QuizStatusDto.of(resultStatus);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "퀴즈를 찾을 수 없습니다.");
    }

    public QuizCommonResultResponseDto getResult(UUID uuid) {
        QuizCommonResultResponseDto result = redisService.getResult(uuid);

        if (result == null) {
            throw new NotFoundException("결과가 만료되었거나 존재하지 않습니다.");

        }
        return result;
    }

    public byte[] downloadQuestion(UUID uuid) throws IOException {
        QuizCommonResultResponseDto result = redisService.getResult(uuid);

        return pdfService.generateQuestionPdf(result);
    }

    public byte[] downloadExplanation(UUID uuid) throws IOException {
        QuizCommonResultResponseDto result = redisService.getResult(uuid);

        return pdfService.generateExplanationPdf(result);
    }
}

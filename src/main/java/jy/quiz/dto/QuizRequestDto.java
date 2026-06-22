package jy.quiz.dto;

import jy.quiz.enums.QuestionCount;
import jy.quiz.enums.QuestionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class QuizRequestDto {

    private final QuestionType questionType;
    private final QuestionCount questionCount;
    private final MultipartFile file;
}

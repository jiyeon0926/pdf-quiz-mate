package jy.quiz.dto;

import jy.quiz.enums.QuestionCount;
import jy.quiz.enums.QuestionType;
import jy.quiz.enums.QuizStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class QuizCommonResponseDto {

    private final String id;
    private final QuestionType questionType;
    private final QuestionCount questionCount;
    private final QuizStatus status;
}

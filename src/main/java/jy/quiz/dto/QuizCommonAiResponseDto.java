package jy.quiz.dto;

import jy.quiz.enums.QuestionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class QuizCommonAiResponseDto {

    private final QuestionType questionType;
    private final int questionCount;
}

package jy.quiz.enums;

import lombok.Getter;

@Getter
public enum QuestionCount {

    FIVE(5),
    TEN(10),
    TWENTY(20);

    private final int count;

    QuestionCount(int count) {
        this.count = count;
    }
}

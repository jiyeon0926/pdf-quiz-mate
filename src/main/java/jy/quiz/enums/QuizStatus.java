package jy.quiz.enums;

public enum QuizStatus {

    PROCESSING("진행 중"),
    COMPLETED("완료"),
    FAILED("실패");

    private final String description;

    QuizStatus(String description) {
        this.description = description;
    }
}

package jy.quiz.controller;

import jy.quiz.enums.QuestionType;
import jy.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/quiz")
    public String generateForm() {
        return "generate";
    }

    @PostMapping("/quiz")
    public String generateQuiz(@RequestParam QuestionType questionType,
                               @RequestParam int questionCount,
                               @RequestParam MultipartFile file) {
        UUID uuid = quizService.generateQuiz(
                questionType,
                questionCount,
                file
        );

        return "redirect:/quiz/" + uuid;
    }
}

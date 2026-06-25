package jy.quiz.controller;

import jy.quiz.enums.QuestionType;
import jy.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    public String generateForm() {
        return "generate";
    }

    @PostMapping
    public String generateQuiz(@RequestParam QuestionType questionType,
                               @RequestParam int questionCount,
                               @RequestParam MultipartFile file) {
        UUID uuid = quizService.generateQuiz(
                questionType,
                questionCount,
                file
        );

        return "redirect:/quiz/" + uuid + "/loading";
    }

    @GetMapping("/{uuid}/loading")
    public String showLoading(@PathVariable UUID uuid, Model model) {
        model.addAttribute("uuid", uuid);

        return "loading";
    }
}

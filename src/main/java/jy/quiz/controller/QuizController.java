package jy.quiz.controller;

import jy.quiz.dto.QuizCommonResultResponseDto;
import jy.quiz.enums.QuestionType;
import jy.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping("/{uuid}")
    public String showResult(@PathVariable UUID uuid, Model model) {
        QuizCommonResultResponseDto result = quizService.getResult(uuid);
        model.addAttribute("uuid", uuid);
        model.addAttribute("result", result);

        return "result";
    }

    @GetMapping("/{uuid}/download/question")
    public ResponseEntity<byte[]> downloadQuestion(@PathVariable UUID uuid) throws IOException {
        byte[] questionByte = quizService.downloadQuestion(uuid);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=question.pdf")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(questionByte);
    }

    @GetMapping("/{uuid}/download/explanation")
    public ResponseEntity<byte[]> downloadExplanation(@PathVariable UUID uuid) throws IOException {
        byte[] explanationByte = quizService.downloadExplanation(uuid);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=explanation.pdf")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(explanationByte);
    }
}

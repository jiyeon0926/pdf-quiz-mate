package jy.quiz.controller;

import jy.quiz.dto.QuizStatusDto;
import jy.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizApiController {

    private final QuizService quizService;

    @GetMapping("/{uuid}/status")
    public ResponseEntity<QuizStatusDto> getStatus(@PathVariable UUID uuid) {
        QuizStatusDto quizStatusDto = quizService.getStatus(uuid);

        return ResponseEntity.ok(quizStatusDto);
    }
}

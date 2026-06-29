package jy.quiz.service;

import jy.quiz.dto.MultipleAiResponseDto;
import jy.quiz.dto.QuizCommonAiResponseDto;
import jy.quiz.dto.ShortAndOxAiResponseDto;
import jy.quiz.enums.QuestionType;
import jy.quiz.exception.ServerCustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiService {

    private final ChatClient chatClient;

    public QuizCommonAiResponseDto generateQuiz(QuestionType type, int count, String text) {
        try {
            String prompt = promptText(type, count, text);

            return switch (type) {
                case MULTIPLE -> chatClient.prompt()
                        .user(prompt)
                        .call()
                        .entity(MultipleAiResponseDto.class);

                case SHORT, OX -> chatClient.prompt()
                        .user(prompt)
                        .call()
                        .entity(ShortAndOxAiResponseDto.class);
            };
        } catch (Exception e) {
            log.error("문제 생성 중 오류 발생");
            throw new ServerCustomException("문제 생성 중 오류 발생: " + e.getMessage(), e);
        }
    }

    private String promptText(QuestionType type, int count, String text) {
        return """
                당신은 PDF 기반 문제 생성 시스템입니다.
                주어진 문서의 내용을 바탕으로 문제 유형에 맞게 문제 수만큼 문제를 생성합니다.
                해설은 문서의 내용을 바탕으로 작성해주세요.
                
                [문서 내용]
                %s
                
                [필수 요청 사항]
                - 문제 유형: %s
                - 생성할 문제 수: %d개
                - JSON 형식으로 반환
                
                문제 유형에 따른 응답 형식이 다릅니다.
                응답 형식을 지켜주세요.
                
                [문제 유형이 4지선다인 응답 형식]
                choice 값마다 텍스트 앞에 번호 붙이지마세요.
                {
                	"questions": [
                		{
                			"questionNumber": 1,
                			"question": 문제,
                			"choices": [
                				{
                					"number": 1,
                					"choice": 보기1
                				},
                				{
                					"number": 2,
                					"choice": 보기2
                				},
                				{
                					"number": 3,
                					"choice": 보기3
                				},
                				{
                					"number": 4,
                					"choice": 보기4
                				}
                			],
                			"answer": {
                				"number": 1,
                				"choice": 정답 보기
                			},
                			"explanation": 해설
                		}
                	]
                }
                
                [문제 유형이 단답형이거나 OX 퀴즈인 응답 형식]
                {
                	"questions": [
                		{
                			"questionNumber": 1,
                			"question": 질문,
                			"answer": 정답
                			"explanation": 해설
                		}
                	]
                }               
                """.formatted(text, type.name(), count);
    }
}

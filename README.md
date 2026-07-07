# 🤖 PDFQuizMate
PDFQuizMate는 사용자가 업로드한 PDF에서 추출한 텍스트를 기반으로 AI가 다양한 유형의 문제를 생성하는 서비스입니다. </br>
4지선다, 단답형, OX 문제를 생성할 수 있으며, 문제와 해설을 각각 PDF 파일로 다운로드하여 학습에 활용할 수 있습니다. </br>

![Project](https://img.shields.io/badge/Project-PDFQuizMate-orange)
![Service](https://img.shields.io/badge/Service-AI%20기반%20문제%20생성%20서비스-blue)
![Period](https://img.shields.io/badge/Period-2026.06.17~2026.07.02-green)

# 📍 목차
[![주요 기능](https://img.shields.io/badge/주요%20기능-FF6B6B?style=for-the-badge)](#주요-기능)
[![기술 스택](https://img.shields.io/badge/기술%20스택-4DABF7?style=for-the-badge)](#기술-스택)
[![요구사항](https://img.shields.io/badge/요구사항-20C997?style=for-the-badge)](#요구사항)
[![설계](https://img.shields.io/badge/설계-845EF7?style=for-the-badge)](#설계)
[![프로젝트 구조](https://img.shields.io/badge/프로젝트%20구조-ADB5BD?style=for-the-badge)](#프로젝트-구조)

## 주요 기능
- 업로드한 PDF에서 추출한 텍스트를 기반으로 Gemini 2.5 Flash를 활용해 4지선다, 단답형, OX 유형의 맞춤형 문제 생성
- 문제 및 해설 PDF 다운로드
<img width="960" height="504" alt="image" src="https://github.com/user-attachments/assets/641f45bd-0f07-4409-860b-3ea0d5100fed" />
<img width="960" height="504" alt="image" src="https://github.com/user-attachments/assets/046d7bac-5987-4220-aa2a-0459aa71ac36" />
<img width="960" height="504" alt="image" src="https://github.com/user-attachments/assets/b49b4b97-0adf-46d3-9b8a-c4ff501830ad" />
<img width="960" height="504" alt="image" src="https://github.com/user-attachments/assets/823f1133-4eeb-4b1b-9a7f-0b922455c7fe" />
<img width="960" height="504" alt="image" src="https://github.com/user-attachments/assets/a8963cf3-e4f6-4b51-a952-f4df7886737f" />
<img width="960" height="504" alt="image" src="https://github.com/user-attachments/assets/f17fd4d6-5fd4-4138-a54a-b0440e2bc347" />

## 기술 스택
## 🛠 Tech Stack

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.5.15-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring_AI_1.0.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Apache PDFBox](https://img.shields.io/badge/Apache_PDFBox-D22128?style=for-the-badge&logo=apache&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![HTML](https://img.shields.io/badge/HTML-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS](https://img.shields.io/badge/CSS-1572B6?style=for-the-badge&logo=css&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Bootstrap](https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![Gemini](https://img.shields.io/badge/Gemini_2.5_Flash-4285F4?style=for-the-badge&logo=googlegemini&logoColor=white)

## 요구사항
- [요구사항 명세서](https://www.notion.so/37d2f0d54a348028b0d3d501c41bc718?source=copy_link)

## 설계
서비스 설계 과정에서 작성한 시스템 흐름, 와이어프레임, AI JSON 응답 구조, Redis 데이터 구조를 확인할 수 있습니다.
- [설계 문서](https://www.notion.so/37d2f0d54a348039aa5af331632927ce?source=copy_link)

## 프로젝트 구조
```
C:.
├─main
│  ├─java
│  │  └─jy
│  │      └─quiz
│  │          │  QuizApplication.java
│  │          │
│  │          ├─config
│  │          │      AsyncConfig.java
│  │          │      ChatClientConfig.java
│  │          │      RedisConfig.java
│  │          │
│  │          ├─controller
│  │          │      QuizApiController.java
│  │          │      QuizController.java
│  │          │
│  │          ├─dto
│  │          │      MultipleAiResponseDto.java
│  │          │      MultipleAnswerDto.java
│  │          │      MultipleChoiceDto.java
│  │          │      MultipleQuestionDto.java
│  │          │      MultipleResultResponseDto.java
│  │          │      QuizCommonAiResponseDto.java
│  │          │      QuizCommonResultResponseDto.java
│  │          │      QuizStatusDto.java
│  │          │      ShortAndOxAiResponseDto.java
│  │          │      ShortAndOxQuestionDto.java
│  │          │      ShortAndOxResultResponseDto.java
│  │          │
│  │          ├─enums
│  │          │      QuestionType.java
│  │          │      QuizStatus.java
│  │          │
│  │          ├─exception
│  │          │      NotFoundException.java
│  │          │      ServerCustomException.java
│  │          │
│  │          └─service
│  │              │  AiService.java
│  │              │  AsyncService.java
│  │              │  QuizService.java
│  │              │  RedisService.java
│  │              │
│  │              └─pdf
│  │                      MultipleExplanationPdfWriter.java
│  │                      MultipleQuestionPdfWriter.java
│  │                      PdfContext.java
│  │                      PdfService.java
│  │                      PdfWriter.java
│  │                      ShortAndOxExplanationPdfWriter.java
│  │                      ShortAndOxQuestionPdfWriter.java
│  │
│  └─resources
│      │  application.properties
│      │
│      ├─fonts
│      │      NanumGothic.ttf
│      │
│      ├─static
│      │  └─css
│      │          bootstrap.min.css
│      │          common.css
│      │
│      └─templates
│              error.html
│              generate.html
│              loading.html
│              result.html
│
└─test
    ├─java
    │  └─jy
    │      └─quiz
    │          │  QuizApplicationTests.java
    │          │
    │          └─service
    │                  AsyncServiceTest.java
    │
    └─resources
            sample.pdf
```

package jy.quiz.service;

import jy.quiz.enums.QuestionType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@Slf4j
class AsyncServiceTest {

    @Autowired
    private AsyncService asyncService;

    @DisplayName("동시에 문제 생성 요청을 보냅니다.")
    @Test
    void generateQuiz() throws InterruptedException {
        // given, when
        int reqCount = 1000;
        int threads = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(reqCount);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        MultipartFile testFile = createTestPdfFile();

        for (int i = 0; i < reqCount; i++) {
            try {
                UUID uuid = UUID.randomUUID();
                asyncService.generateQuiz(uuid, QuestionType.MULTIPLE, 20, testFile);
                successCount.incrementAndGet();
            } catch (Exception e) {
                failCount.incrementAndGet();
                log.error("실패: {}번째", i, e);
            } finally {
                latch.countDown();
            }
        }

        latch.await(60, TimeUnit.SECONDS);
        executorService.shutdown();

        // then
        log.info("성공: {}", successCount);
        log.info("실패: {}", failCount);
    }

    private MultipartFile createTestPdfFile() {
        try {
            byte[] file = Files.readAllBytes(Paths.get("src/test/resources/sample.pdf"));

            return new MockMultipartFile("file", "sample.pdf", "application/pdf", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

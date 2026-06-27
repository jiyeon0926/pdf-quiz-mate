package jy.quiz.service;

import jy.quiz.exception.ServerCustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class PdfService {

    /**
     * PDF 파일로부터 텍스트 추출
     *
     * @param pdfFile
     * @return 추출된 텍스트
     * @throws ServerCustomException 텍스트 추출 실패 시
     */
    public String extractTextFromPdf(MultipartFile pdfFile) {
        log.info("PDF 텍스트 추출 시작: {}", pdfFile.getName());

        try {
            PDDocument document = PDDocument.load(pdfFile.getInputStream());
            log.debug("PDF 문서 로드 성공: {}페이지", document.getNumberOfPages());

            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String text = pdfTextStripper.getText(document);
            log.debug("PDF 텍스트 추출 완료: {} 문자", text.length());

            return text;
        } catch (IOException e) {
            log.error("PDF 텍스트 추출 실패");
            throw new ServerCustomException("PDF에서 텍스트 추출 실패: " + e.getMessage(), e);
        }
    }
}

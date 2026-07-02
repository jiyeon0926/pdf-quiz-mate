package jy.quiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 서버 오류 관련 커스텀 예외
 */
public class ServerCustomException extends ResponseStatusException {

    public ServerCustomException(String message) {
        this(message, null);
    }

    public ServerCustomException(String message, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
    }
}

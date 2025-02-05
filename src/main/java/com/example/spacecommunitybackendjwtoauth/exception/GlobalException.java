package com.example.spacecommunitybackendjwtoauth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

// Global Exception
@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(SpaceCommunityAuthException.class)
    public ResponseEntity<ErrorResponse> handleSpaceAuthException(SpaceCommunityAuthException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(ErrorResponse.from(
                e.getHttpStatus().value(),
                e.getErrorCode(),
                e.getMessage()
        ));
    }

    @ExceptionHandler(SpaceCommunityRunTimeException.class)
    public ResponseEntity<ErrorResponse> handleSpaceRuntimeException(SpaceCommunityRunTimeException e) {
        return ResponseEntity.status(e.getStatus()).body(ErrorResponse.from(
                e.getStatus().value(),
                e.getStatus().name(),
                e.getErrorMessage()
        ));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestPartException() {
        return ResponseEntity.badRequest().body(ErrorResponse.from(
                400,
                "INVALID_INPUT",
                "인자값이 제대로 들어오지 않았습니다."
        ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleDefineException() {
        return ResponseEntity.status(400).body(ErrorResponse.from(
                400,
                "INVALID_INPUT",
                "데이터 타입이 일치하지 않습니다."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException() {
        return ResponseEntity.status(400).body(ErrorResponse.from(
                400,
                "UNVERIFIED_INPUT",
                "데이터 값이 유효하지 않습니다"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException() {
        return ResponseEntity.status(400).body(ErrorResponse.from(
                400,
                "INVALID_INPUT",
                "잘못된 값이 들어왔습니다."));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(400).body(ErrorResponse.from(
                400,
                "INVALID_INPUT" ,
                "잘못된 NULL값이 들어갔습니다."));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(500).body(ErrorResponse.from(
                500,
                "SERVER_UNKNOWN",
                "서버에서 알 수 없는 에러가 발생했습니다."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(500).body(ErrorResponse.from(
                500,
                "SERVER_UNKNOWN",
                "서버에서 알 수 없는 에러가 발생했습니다."));
    }
}

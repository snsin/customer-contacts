package ru.test.task.customercontacts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    private static ProblemDetail createProblemDetail(String message, HttpStatus httpStatus) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);
        problemDetail.setProperty("timestamp", ZonedDateTime.now());
        return problemDetail;
    }

    private static void warn(RuntimeException ex) {
        log.warn("exception: {}, message: {}", ex.getClass().getName(), ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ProblemDetail notFound(NoSuchElementException ex) {
        warn(ex);
        return createProblemDetail(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ProblemDetail badRequest(RuntimeException ex) {
        warn(ex);
        return createProblemDetail(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ProblemDetail unprocessable(DataIntegrityViolationException ex) {
        log.error(ex.getMessage(), ex);
        return createProblemDetail("can't save or update entity", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ProblemDetail rest(Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
        return createProblemDetail("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

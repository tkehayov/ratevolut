package com.zetta.ratevolut.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RateNotFoundException.class)
    public ProblemDetail handleRateNotFound(RateNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("RATE_NOT_FOUND");
        problem.setDetail(ex.getMessage());

        return problem;
    }

    @ExceptionHandler(InvalidCurrencyException.class)
    public ProblemDetail handleInvalidCurrency(InvalidCurrencyException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("INVALID_CURRENCY");
        problem.setDetail(ex.getMessage());

        return problem;
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ProblemDetail handleInsufficientFundsException(InsufficientFundsException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_CONTENT);
        problem.setTitle("INSUFFICIENT_FUNDS");
        problem.setDetail(ex.getMessage());

        return problem;
    }
    @ExceptionHandler(BalanceNotFoundException.class)
    public ProblemDetail handleBalanceNotFoundException(BalanceNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_CONTENT);
        problem.setTitle("BALANCE_NOT_FOUND");
        problem.setDetail(ex.getMessage());

        return problem;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        String detail = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setDetail(detail);

        return problem;
    }

}
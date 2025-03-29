package com.kardibus.temp.exeption

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(InvalidInputException::class)
    fun handleInvalidInputException(
        e: InvalidInputException,
        request: WebRequest?,
    ): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.message)
        return problemDetail
    }

    @ExceptionHandler(BusinessInputException::class)
    fun handleBusinessInputException(
        e: BusinessInputException,
        request: WebRequest?,
    ): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.message)
        return problemDetail
    }
}

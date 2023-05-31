package com.zerobase.api.exception

import com.zerobase.api.loan.review.LoanReviewController
import com.zerobase.api.user.controller.UserInfoController
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [LoanReviewController::class, UserInfoController::class])
class ControllerAdvice {
    @ExceptionHandler(CustomException::class)
    fun customExceptionHandler(customException: CustomException) =
        ErrorResponse(customException).toResponseEntity()
}
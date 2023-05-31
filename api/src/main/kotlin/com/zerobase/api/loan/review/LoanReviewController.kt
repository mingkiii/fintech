package com.zerobase.api.loan.review

import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fintech/api/v1")
class LoanReviewController(
    private val loanReviewServiceImpl: LoanReviewServiceImpl
) {

    @GetMapping("review/{userKey}")
    @ApiOperation(value = "대출 심사 결과 받는 API", notes = "신용 평가 정보원으로부터 대출 심사 결과를 받는 API")
    fun getReviewData(
        @PathVariable userKey: String
    ): ResponseEntity<LoanReviewDto.LoanReviewResponseDto> {
        return ResponseEntity.ok(
            loanReviewServiceImpl.loanReviewMain(userKey)
        )
    }
}
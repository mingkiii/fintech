package com.zerobase.api.loan.request

import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fintech/api/v1")
class LoanRequestController(
    private val loanRequestServiceImpl: LoanRequestServiceImpl
) {
    @PostMapping("/request")
    @ApiOperation(value = "대출 심사 요청 API", notes = "신용 평가 정보원에 대출 심사 요청하는 API")
    fun loanRequest(
        @RequestBody loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): ResponseEntity<LoanRequestDto.LoanRequestResponseDto> {
        return ResponseEntity.ok(
            loanRequestServiceImpl.loanRequestMain(loanRequestInputDto)
        )
    }
}
package com.zerobase.api.user.model

import com.zerobase.domain.domain.UserInfo
import com.zerobase.kafka.dto.LoanRequestDto

data class UserInfoDto(
    val userKey: String,
    val userName: String,
    val userRegistrationNumber: String,
    val userIncomeAmount: Long
) {
    fun toEntity(): UserInfo =
        UserInfo(
            userKey, userRegistrationNumber, userName, userIncomeAmount
        )

    fun toLoanRequestKafkaDto() = LoanRequestDto(userKey, userName, userIncomeAmount, userRegistrationNumber)
}
data class UserInfoRequestDto(
    val userName: String,
    val userIncomeAmount: Long,
    var userRegistrationNumber: String
) {
    fun toUserInfoDto(userKey: String) =
        UserInfoDto(
            userKey, userName, userRegistrationNumber, userIncomeAmount
        )
}
data class UserInfoResponseDto(
    val userKey: String
)

data class GetUserInfoResponseDto(
    val userKey: String,
    val userRegistrationNumber: String
)
package com.zerobase.api.user.service

import com.zerobase.api.user.model.GetUserInfoResponseDto
import com.zerobase.api.user.model.UserInfoRequestDto
import com.zerobase.api.user.model.UserInfoResponseDto
import com.zerobase.domain.Encrypt

interface UserInfoService {
    @Encrypt
    fun createUserInfo(
        userInfoRequestDto: UserInfoRequestDto
    ): UserInfoResponseDto

    @Encrypt
    fun getUserInfo(userKey: String): GetUserInfoResponseDto
}
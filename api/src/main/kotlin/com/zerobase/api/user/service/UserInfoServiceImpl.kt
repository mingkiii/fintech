package com.zerobase.api.user.service

import com.zerobase.api.encrypt.GenerateKey
import com.zerobase.api.exception.CustomErrorCode
import com.zerobase.api.exception.CustomException
import com.zerobase.api.user.model.GetUserInfoResponseDto
import com.zerobase.api.user.model.UserInfoRequestDto
import com.zerobase.api.user.model.UserInfoResponseDto
import com.zerobase.domain.Encrypt
import com.zerobase.domain.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class UserInfoServiceImpl(
    private val userInfoRepository: UserInfoRepository,
    private val generateKey: GenerateKey
) : UserInfoService {
    @Encrypt
    override fun createUserInfo(
        userInfoRequestDto: UserInfoRequestDto): UserInfoResponseDto {
        val userKey = generateKey.generateUserKey()

        val userInfoDto =
            userInfoRequestDto.toUserInfoDto(userKey)

        userInfoRepository.save(userInfoDto.toEntity())

        return UserInfoResponseDto(userKey)
    }

    @Encrypt
    override fun getUserInfo(userKey: String): GetUserInfoResponseDto {
        val userInfo = userInfoRepository.findByUserKey(userKey)
        if (!userInfo.isPresent) {
            throw CustomException(CustomErrorCode.USER_NOT_FOUND)
        }

        return GetUserInfoResponseDto(
            userKey = userKey,
            userRegistrationNumber = userInfo.get().userRegistrationNumber)
    }
}
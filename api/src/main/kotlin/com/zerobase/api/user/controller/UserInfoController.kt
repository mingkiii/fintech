package com.zerobase.api.user.controller

import com.zerobase.api.user.model.GetUserInfoResponseDto
import com.zerobase.api.user.model.UserInfoRequestDto
import com.zerobase.api.user.model.UserInfoResponseDto
import com.zerobase.api.user.service.UserInfoServiceImpl
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fintech/v1/user")
class UserInfoController(
    private val userInfoServiceImpl: UserInfoServiceImpl
) {
    @PostMapping("/infomation")
    @ApiOperation(value = " 유저 정보 수신 API", notes = "유저 정보를 받는 API")
    fun createUserInfo(
        @RequestBody userInfoRequestDto: UserInfoRequestDto
    ): ResponseEntity<UserInfoResponseDto> {
        return ResponseEntity.ok(
            userInfoServiceImpl.createUserInfo(userInfoRequestDto)
        )
    }

    @GetMapping("/private-info/{userKey}")
    @ApiOperation(value = "유저 정보 조회 API", notes = "유저 정보를 조회하는 API")
    fun getUserInfo(
        @PathVariable userKey: String
    ): ResponseEntity<GetUserInfoResponseDto> {
        return ResponseEntity.ok(userInfoServiceImpl.getUserInfo(userKey))
    }
}
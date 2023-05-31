package com.zerobase.api.loan.request

import com.zerobase.api.encrypt.GenerateKey
import com.zerobase.api.encrypt.EncryptComponent
import com.zerobase.api.user.model.UserInfoDto
import com.zerobase.domain.Encrypt
import com.zerobase.domain.repository.UserInfoRepository
import com.zerobase.kafka.enum.KafkaTopic
import com.zerobase.kafka.producer.LoanRequestSender
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository,
    private val loanRequestSender: LoanRequestSender
): LoanRequestService {

    override fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto {
        val userKey = generateKey.generateUserKey()

        val userInfoDto = loanRequestInputDto.toUserInfoDto(userKey)

        saveUserInfo(userInfoDto) // db 에 저장

        loanRequestReview(userInfoDto)  // kafka로 넘겨줌

        return LoanRequestDto.LoanRequestResponseDto(userKey)
    }

    @Encrypt
    override fun saveUserInfo(userInfoDto: UserInfoDto) =
        userInfoRepository.save(userInfoDto.toEntity())

    override fun loanRequestReview(userInfoDto: UserInfoDto) {
        loanRequestSender.sendMessage(
            KafkaTopic.LOAN_REQUEST,
            userInfoDto.toLoanRequestKafkaDto()
        )
    }

}
package com.zerobase.api.aop

import com.zerobase.api.encrypt.EncryptComponent
import com.zerobase.domain.domain.UserInfo
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import java.util.*

@Aspect
@Component
class EncryptionAspect(
    private val encryptComponent: EncryptComponent
) {
    @Pointcut("execution(* com.zerobase.domain.repository.*.save(..))")
    fun savePointcut() {}

    @Pointcut("execution(* com.zerobase.domain.repository.*.find*(..))")
    fun findPointcut() {}

    @Before("savePointcut()")
    fun encryptFieldsBeforeSave(joinPoint: JoinPoint) {
        val args = joinPoint.args
        for (arg in args) {
            if (arg is UserInfo) {
                arg.userRegistrationNumber = encryptComponent.encryptString(arg.userRegistrationNumber)
            }
        }
    }

    @AfterReturning(pointcut = "findPointcut()", returning = "result")
    fun decryptFieldsAfterFind(joinPoint: JoinPoint, result: Any?) {
        if (result is Optional<*>) {
            val optionalResult = result as Optional<UserInfo>
            if (optionalResult.isPresent) {
                val userInfo = optionalResult.get()
                userInfo.userRegistrationNumber = encryptComponent.decryptString(userInfo.userRegistrationNumber)
            }
        } else if (result is UserInfo) {
            result.userRegistrationNumber = encryptComponent.decryptString(result.userRegistrationNumber)
        }
    }
}
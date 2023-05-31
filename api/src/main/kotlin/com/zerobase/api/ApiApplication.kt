package com.zerobase.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.EnableAspectJAutoProxy
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

@SpringBootApplication
@EntityScan(basePackages = ["com.zerobase.domain"])
@ComponentScan(basePackages = ["com.zerobase"])
@EnableAspectJAutoProxy
@EnableCaching
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
//fun main() {
//    val url = "jdbc:mysql://fintech-mysql:3306/fintech?characterEncoding=UTF-8&serverTimezone=Asia/Seoul"
//    val username = "fintech"
//    val password = "fintech"
//
//    try {
//        val connection: Connection = DriverManager.getConnection(url, username, password)
//        println("접속 성공!")
//        connection.close()
//    } catch (e: SQLException) {
//        println("접속 실패: ${e.message}")
//    }
//}
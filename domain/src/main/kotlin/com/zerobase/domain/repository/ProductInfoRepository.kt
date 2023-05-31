package com.zerobase.domain.repository

import com.zerobase.domain.domain.ProductInfo
import com.zerobase.domain.type.Organization
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductInfoRepository : JpaRepository<ProductInfo, Long> {
    fun findByOrgCd(orgCd: String): Optional<List<ProductInfo>>
}
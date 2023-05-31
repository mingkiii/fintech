package com.zerobase.api.product.controller

import com.zerobase.api.product.model.ProductDto
import com.zerobase.api.product.service.ProductServiceImpl
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.cache.annotation.CacheEvict
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/fintech/v1/product")
class ProductInfoController(
    private val productServiceImpl: ProductServiceImpl
) {
    @GetMapping("/{organizationCode}")
    @ApiOperation(value = "상품 정보 조회 API", notes = "상품 정보를 조회하는 API 입니다.")
    @ApiImplicitParam(name = "organizationCode", value = "기관 코드" )
    fun getProduct(
        @PathVariable organizationCode: String
    ): ResponseEntity<MutableList<ProductDto.GetProductResponseDto>> {
        return ResponseEntity.ok(productServiceImpl.getProduct(organizationCode))
    }

    @PostMapping("/information")
    @CacheEvict(value = ["products"], allEntries = true)
    @ApiOperation(value = "상품 정보 수신 API", notes = "금융사로부터 상품 정보를 받는 API 입니다.")
    fun saveProduct(
        @RequestBody saveProductRequestDto: ProductDto.SaveProductRequestDto
    ): ResponseEntity<String> {
        return ResponseEntity.ok(productServiceImpl.saveProduct(saveProductRequestDto))
    }
}
package com.zerobase.api.product.service

import com.zerobase.api.product.model.ProductDto
import com.zerobase.domain.domain.ProductInfo

interface ProductService {
    fun getProduct(organizationCode: String): MutableList<ProductDto.GetProductResponseDto>

    fun getProductList(organizationCode: String): List<ProductInfo>

    //------------------------------------------------------------------------

    fun saveProduct(saveProductRequestDto: ProductDto.SaveProductRequestDto): String

    fun saveProductInfo(saveProductRequestDto: ProductDto.SaveProductRequestDto)

    fun saveProductList(saveProductRequestDto: ProductDto.SaveProductRequestDto)
}
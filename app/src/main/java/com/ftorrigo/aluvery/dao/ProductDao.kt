package com.ftorrigo.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import com.ftorrigo.aluvery.model.Product

class ProductDao {

    companion object {
        private val products = mutableStateListOf<Product>()
    }

    fun products() = products.toList()

    fun save(product: Product) {
        products.add(product)
    }
}
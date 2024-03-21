package com.ftorrigo.aluvery.sampleData

import com.ftorrigo.aluvery.R
import com.ftorrigo.aluvery.model.Product
import java.math.BigDecimal

val mockProducts = listOf(
    Product(
        name = "Hamburguer",
        image = R.drawable.hamburguer,
        price = BigDecimal("18.90")
    ),
    Product(
        name = "Pizza",
        image = R.drawable.pizza,
        price = BigDecimal("34.90")
    ),
    Product(
        name = "batata frita",
        image = R.drawable.batata_frita,
        price = BigDecimal("12.90")
    )
)
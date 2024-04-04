package com.ftorrigo.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts
import com.ftorrigo.aluvery.dao.ProductDao
import com.ftorrigo.aluvery.model.Product
import com.ftorrigo.aluvery.ui.states.HomeScreenUiState
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val dao = ProductDao()

    var uiState: HomeScreenUiState by mutableStateOf(
        HomeScreenUiState(
            onSearchChange = {
                uiState = uiState.copy(
                    searchText = it,
                    filteredProducts = filterProducts(it)
                )
            }
        ))
        private set


    init {
        viewModelScope.launch {
            dao.products().collect { products ->
                uiState = uiState.copy(
                    sections = mapOf(
                        "Todos produtos" to products,
                        "Promoções" to sampleDrinks + sampleCandies,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    )
                )
            }
        }
    }

    private fun containsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(
            text, true
        ) || product.description?.contains(
            text, true
        ) ?: false
    }

    private fun filterProducts(text: String): List<Product> =
        if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription(text)) + dao.products().value.filter(
                containsInNameOrDescription(text)
            )
        } else emptyList()
}
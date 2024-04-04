package com.ftorrigo.aluvery.ui.states

import com.ftorrigo.aluvery.model.Product

data class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val filteredProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (text: String) -> Unit = {}
) {
    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }
}
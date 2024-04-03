package com.ftorrigo.aluvery.ui.screens

import ProductSection
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.sampledata.sampleShopSections
import com.ftorrigo.aluvery.model.Product
import com.ftorrigo.aluvery.ui.components.CardProductItem
import com.ftorrigo.aluvery.ui.components.PartnersSection
import com.ftorrigo.aluvery.ui.components.SearchTextField
import com.ftorrigo.aluvery.ui.theme.AluveryTheme
import com.ftorrigo.aluvery.viewmodels.HomeScreenViewModel

class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val filteredProducts: List<Product> = emptyList(),
    val products: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    products: List<Product>
) {
    val sections = mapOf(
        "Todos produtos" to products,
        "Promoções" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )
    var text by rememberSaveable {
        mutableStateOf("")
    }

    fun containsInNameOrDescription() = { product: Product ->
        product.name.contains(text, true) || product.description?.contains(
            text, true
        ) ?: false
    }

    val filteredProducts = rememberSaveable(text, products) {
        if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription()) + products.filter(
                containsInNameOrDescription()
            )
        } else emptyList()
    }

    val state = viewModel.uiState
    HomeScreen(state = state)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
) {

    val sections = state.sections
    val text = state.searchText
    val filteredProducts = state.filteredProducts

    Column {

        SearchTextField(
            searchText = text, onSearchChange = state.onSearchChange
        )

        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {

            if (state.isShowSections()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductSection(
                            title = title, productsList = products
                        )
                    }
                }

                for (shopSections in sampleShopSections) {
                    val title = shopSections.key
                    val shop = shopSections.value
                    item {
                        PartnersSection(title = title, shop = shop)
                    }
                }
            } else {
                items(filteredProducts) { p ->
                    CardProductItem(product = p, Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(HomeScreenUiState(sections = sampleSections))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenWithSearchTextPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(
                state = HomeScreenUiState(
                    searchText = "a",
                    sections = sampleSections
                )
            )
        }
    }
}
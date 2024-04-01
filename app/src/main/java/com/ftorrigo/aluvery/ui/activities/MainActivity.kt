package com.ftorrigo.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import com.ftorrigo.aluvery.dao.ProductDao
import com.ftorrigo.aluvery.model.Product
import com.ftorrigo.aluvery.ui.screens.HomeScreen
import com.ftorrigo.aluvery.ui.screens.HomeScreenUiState
import com.ftorrigo.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {

    private val dao = ProductDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            }) {
                val products = dao.products()
                val sections = mapOf(
                    "Todos produtos" to products,
                    "Promoções" to sampleDrinks + sampleCandies,
                    "Doces" to sampleCandies,
                    "Bebidas" to sampleDrinks
                )
                var text by remember {
                    mutableStateOf("")
                }

                fun containsInNameOrDescription() = { product: Product ->
                    product.name.contains(text, true) || product.description?.contains(
                        text, true
                    ) ?: false
                }

                val filteredProducts = remember(text, products) {
                    if (text.isNotBlank()) {
                        sampleProducts.filter(containsInNameOrDescription()) + products.filter(
                            containsInNameOrDescription()
                        )
                    } else emptyList()
                }

                val state = remember(products, text) {
                    HomeScreenUiState(
                        sections = sections,
                        filteredProducts = filteredProducts,
                        products = products,
                        searchText = text,
                        onSearchChange = {
                            text = it
                        }
                    )
                }
                HomeScreen(state = state)
            }
        }
    }
}

@Composable
fun App(onFabClick: () -> Unit = {}, content: @Composable () -> Unit = {}) {
    AluveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                }
            }) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }

            }
        }
    }
}

@Preview
@Composable
private fun AppPreview() {
    App {
        HomeScreen(HomeScreenUiState(sections = sampleSections))
    }
}
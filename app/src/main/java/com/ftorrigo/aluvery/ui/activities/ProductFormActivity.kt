package com.ftorrigo.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.ftorrigo.aluvery.dao.ProductDao
import com.ftorrigo.aluvery.ui.screens.ProductFormScreen
import com.ftorrigo.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen(onSaveClick = { product ->
                        dao.save(product)
                        finish()
                    })
                }
            }
        }
    }

}


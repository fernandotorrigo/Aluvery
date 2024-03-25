package com.ftorrigo.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(searchText: String, onSearchChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue -> onSearchChange(newValue) },
        Modifier
            .padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(100),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Icone search")
        },
        label = {
            Text(text = "Produto")
        },
        placeholder = {
            Text(text = "O que voçê espera?")
        }
    )
}
package com.ftorrigo.aluvery.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ftorrigo.aluvery.ui.screens.HomeScreenUiState

class HomeScreenViewModel : ViewModel() {
    var uiState by mutableStateOf(HomeScreenUiState())
        private set


}
package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansDataApplication
import com.example.amphibians.data.AmphibiansDataRepository
import com.example.amphibians.model.Amphibian
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState {
    data class Success(val amphibiansData: List<Amphibian>): AmphibiansUiState
    object Error: AmphibiansUiState
    object Loading: AmphibiansUiState
}

class AmphibiansViewModel(private val amphibiansDataRepository: AmphibiansDataRepository): ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibiansData()
    }

    fun getAmphibiansData() {
        viewModelScope.launch {
            amphibiansUiState = try {
                val data = amphibiansDataRepository.getAmphibiansData()
                AmphibiansUiState.Success(amphibiansData = data)
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            } catch (e: IOException) {
                AmphibiansUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansDataApplication)
                val amphibiansDataRepository = application.container.amphibiansDataRepository
                AmphibiansViewModel(amphibiansDataRepository = amphibiansDataRepository)
            }
        }
    }
}
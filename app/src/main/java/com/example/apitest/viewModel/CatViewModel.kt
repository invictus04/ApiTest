package com.example.apitest.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apitest.data.Breed
import com.example.apitest.data.BreedResponse
import com.example.apitest.data.CatsFacts
import com.example.apitest.service.catService
import kotlinx.coroutines.launch

class CatViewModel:ViewModel() {
    private val _facts = mutableStateOf(CatState())
    val facts: State<CatState> = _facts

    private val _breed = mutableStateOf(BreedState())
    val breed: State<BreedState> = _breed

    private val _allFacts = mutableStateOf(CatFactsState())
    val allFacts: State<CatFactsState> = _allFacts

    init {
        fetchfacts()
        fetchListOfBreeds()
        fetchAllFacts()
    }

     fun fetchAllFacts() {
        viewModelScope.launch {
            try {
                val response = catService.getAllFacts()
                _allFacts.value = _allFacts.value.copy(
                    list = response.data,
                    loading = false,
                    error = null
                )
            } catch (e: Exception){
                _allFacts.value = _allFacts.value.copy(
                    loading = false,
                    error = "${e.message}"
                )
            }
        }
    }

    fun fetchListOfBreeds() {
        viewModelScope.launch {
            try {
                val response = catService.getBreeds()
                    _breed.value = _breed.value.copy(
                        list = response.data,
                        loading = false,
                        error = null
                    )
            } catch (e: Exception){
                    _breed.value = _breed.value.copy(
                        loading = false,
                        error = "${e.message}"
                    )
            }
        }
    }

    fun fetchfacts() {
        viewModelScope.launch {
            try {
                val response = catService.getFacts()
                _facts.value = _facts.value.copy(
                    fact = response.fact,
                    loading = false,
                    error = null
                )
            } catch (e: Exception){
                _facts.value = _facts.value.copy(
                    loading = false,
                    error = "Error Fetching ${e.message}"
                )
            }
        }
    }
}

data class CatState (
    val fact: String = "",
    val loading: Boolean = true,
    val error: String? = null
)

data class BreedState(
    val list: List<Breed> = emptyList(),
    val loading: Boolean = true,
    val error: String? = null
)

data class CatFactsState(
    val list: List<CatsFacts> = emptyList(),
    val loading: Boolean = true,
    val error: String? = null
)



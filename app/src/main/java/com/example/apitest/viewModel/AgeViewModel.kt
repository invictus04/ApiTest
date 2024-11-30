package com.example.apitest.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitest.service.ageService
import kotlinx.coroutines.launch

class AgeViewModel() : ViewModel(){
    private val _age = mutableStateOf(AgeState())
    val age: State<AgeState> = _age

//    init {
//        fetchAge(name)
//    }

    fun fetchAge(name: String) {
        viewModelScope.launch {
            try {
                val response =  ageService.getAge(name)
                _age.value = _age.value.copy(
                    name = response.name,
                    age = response.age,
                    count = response.count,
                    loading = false,
                    error = null
                )
            }catch (e: Exception){
                _age.value = _age.value.copy(
                    loading = false,
                    error = "${e.message}"
                )
            }
        }
    }


}

data class AgeState(
    val name: String? = null,
    val age: Int? = null,
    val count: Int? = null,
    val loading: Boolean = true,
    val error: String? = null
)
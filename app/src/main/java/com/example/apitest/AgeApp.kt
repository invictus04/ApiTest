package com.example.apitest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apitest.viewModel.AgeViewModel


@Composable
fun AgeApp(ageViewModel: AgeViewModel= viewModel()) {
    val ageState by ageViewModel.age
    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = text ,
            onValueChange = {text=it},
            modifier = Modifier.padding(10.dp))

        Button(onClick = {
            ageViewModel.fetchAge(text)
        },
            modifier = Modifier.padding(10.dp)) {
            Text(text = "Send")
        }
        when{
            ageState.loading -> CircularProgressIndicator()
            ageState.error != null -> Text(text = "${ageState.error}")
           else -> Text(text = "The name is ${ageState.name} and the age is ${ageState.age} and the count is ${ageState.count}")
        }
    }
}
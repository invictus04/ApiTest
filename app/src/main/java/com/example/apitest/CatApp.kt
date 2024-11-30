package com.example.apitest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apitest.data.Breed
import com.example.apitest.data.CatsFacts
import com.example.apitest.viewModel.CatViewModel

@Preview(showBackground = true)
@Composable
fun CatApp(catViewModel: CatViewModel = viewModel()) {
    val catState by catViewModel.facts
    val breedState by catViewModel.breed
    val catState2 by catViewModel.allFacts
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        when{
            catState.loading -> CircularProgressIndicator()
            catState.error!= null -> Text(text = "Error: ${catState.error}", textAlign = TextAlign.Center)
            else -> Text(text = "Random facts: ${catState.fact}", textAlign = TextAlign.Center)
        }

        Button(onClick = {
            catViewModel.fetchfacts()
            catViewModel.fetchListOfBreeds()
            catViewModel.fetchAllFacts()
        }) {
            Text(text = "Refresh")
        }

        Spacer(modifier = Modifier.height(10.dp))

        when{
            breedState.loading -> CircularProgressIndicator()
            breedState.error != null -> Text(text = "${breedState.error}")
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .size(250.dp)
                        .padding(10.dp)
                ) {
                    items(breedState.list.size){ index ->
                        val breed = breedState.list[index]
                        BreedItem(breed)
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(10.dp))

        when{
            catState2.loading -> CircularProgressIndicator()
            catState2.error != null -> Text(text = "${catState2.error}")
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .height(250.dp)
                        .padding(10.dp)
                ) {
                    items(catState2.list.size){index ->
                        val allfacts = catState2.list[index]
                        FactsItem(allfacts)
                    }
                }
            }
        }
    }
}

@Composable
fun FactsItem(fact: CatsFacts) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Facts: ${fact.fact}")
        Text(text = "Facts: ${fact.length}")
    }
}

@Composable
fun BreedItem(breed: Breed) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Breed: ${breed.breed}")
        Text(text = "Coat: ${breed.coat}")
        Text(text = "Origin: ${breed.origin}")
        Text(text = "Country: ${breed.country}")
        Text(text = "Pattern: ${breed.pattern}")
    }
}

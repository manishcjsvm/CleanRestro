package com.assignment.presentation.features.homescreen


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.assignment.presentation.R
import com.assignment.presentation.composables.Error
import com.assignment.presentation.composables.ProgressBar
import com.assignment.presentation.features.homescreen.composables.CharactersList

@Composable
fun CharactersListScreen(onItemClick: (id: Int) -> Unit) {


    val viewModel: CharactersListViewModel = hiltViewModel()

    val state = viewModel.stateFlow.collectAsState()


    when (val currentState = state.value) {

        is CharacterListViewState.Loading -> {
            ProgressBar(modifier = Modifier.fillMaxSize())
        }

        is CharacterListViewState.Success -> {

            CharactersList(characterList = currentState.data, onItemClick = {
                viewModel.sendIntent(CharacterListViewIntent.OnItemClicked(it))
            })
        }

        is CharacterListViewState.Error -> {
            Error(
                errorMessage = currentState.errorMessage
            )
        }
    }


    LaunchedEffect(key1 = Unit) {

        viewModel.sideEffectFlow.collect()
        {
            onItemClick(it.id)
        }
    }
}

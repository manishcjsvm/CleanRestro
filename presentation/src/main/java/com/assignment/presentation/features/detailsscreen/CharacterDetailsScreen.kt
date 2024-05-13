package com.assignment.presentation.features.detailsscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.assignment.presentation.composables.Error
import com.assignment.presentation.composables.ProgressBar
import com.assignment.presentation.features.detailsscreen.composables.CharacterDetailsCard

/**
 * Character details screen to be shown when user selects the character on the characters list screen.
 */
@Composable
fun CharacterDetailsScreen() {

    val viewModel: CharacterDetailsViewModel = hiltViewModel()


    val state = viewModel.stateFlow.collectAsState()


    when (val currentState = state.value) {

        is CharacterDetailsViewState.Loading -> {
            ProgressBar(modifier = Modifier.fillMaxSize())
        }

        is CharacterDetailsViewState.Success -> {
            CharacterDetailsCard(modifier = Modifier.padding(5.dp), currentState.data)
        }

        is CharacterDetailsViewState.Error -> {
            Error(
                errorMessage = currentState.errorMessage
            )
        }
    }
}
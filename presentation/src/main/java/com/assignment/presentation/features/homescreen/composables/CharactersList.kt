package com.assignment.presentation.features.homescreen.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.assignment.presentation.models.CharacterList

@Composable
fun CharactersList(
    modifier: Modifier = Modifier,
    characterList: CharacterList,
    onItemClick: (Int) -> Unit
) {

    LazyColumn {

        items(characterList.charactersList)
        {
            CharacterListRow(modifier = modifier, it, onItemClick)
        }
    }
}
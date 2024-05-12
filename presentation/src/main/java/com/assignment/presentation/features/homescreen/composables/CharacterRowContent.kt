package com.assignment.presentation.features.homescreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.assignment.presentation.R
import com.assignment.presentation.models.Character

@Composable
fun CharacterRowContent(modifier: Modifier = Modifier, character: Character) {

    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceAround) {

        with(character)
        {
            Text(text = name, style = MaterialTheme.typography.titleLarge)
            Text(
                text = stringResource(id = R.string.createdAt, createdAt),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = stringResource(id = R.string.updatedAt, updatedAt),
                style = MaterialTheme.typography.bodySmall
            )

        }
    }

}


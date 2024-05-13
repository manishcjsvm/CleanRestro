package com.assignment.presentation.features.detailsscreen.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.assignment.presentation.R
import com.assignment.presentation.composables.Avatar
import com.assignment.presentation.models.Character

/**
 * To be used to show the character details on the screen.
 *
 * @param modifier to modify the composable.
 * @param character model to load the data from.
 */
@Composable
fun CharacterDetailsCard(
    modifier: Modifier = Modifier, character: Character
) {

    Column(Modifier.verticalScroll(rememberScrollState())) {
        Card(
            modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            with(character) {
                Column {
                    Avatar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), imageUrl = imageUrl
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.name, name),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.films, films.joinToString()))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.createdAt, createdAt))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(id = R.string.updatedAt, updatedAt))
            }

        }
    }
}
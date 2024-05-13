package com.assignment.presentation.base

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.assignment.presentation.R

/**
 * Base Screen to be used for providing common UI components to the screen.
 *
 * @param title title of the screen.
 * @param backButtonEnabled to show back button on top left of the screen.
 * @param onBackButtonClick callback when there will be back button clicked.
 * @param content content to be passed.
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    title: String,
    backButtonEnabled: Boolean = false,
    onBackButtonClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    Scaffold(topBar = {
        TopAppBar(title = {
            Row(verticalAlignment = Alignment.CenterVertically) {

                if (backButtonEnabled) IconButton(onClick = onBackButtonClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_arrow)
                    )
                }
                Text(text = title)
            }
        })
    }) {

        Surface(modifier = Modifier.padding(it)) {
            content()
        }
    }

}
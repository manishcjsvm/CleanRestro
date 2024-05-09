package com.assignment.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.cleanrestro.ui.theme.CleanRestroTheme

/**
 * To be used a progress bar while loading the data.
 *
 * @param modifier to modify the composable.
 *
 */
@Composable
fun ProgressBar(modifier: Modifier = Modifier) {

    Box(modifier = modifier.size(100.dp), contentAlignment = Alignment.Center)
    {
        CircularProgressIndicator(modifier = Modifier.size(40.dp), color = Color.Red)
    }

}

@Preview(showSystemUi = true)
@Composable
fun PreviewProgressBar() {
    CleanRestroTheme {
        ProgressBar()
    }
}
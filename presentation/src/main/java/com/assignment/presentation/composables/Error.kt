package com.assignment.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignment.cleanrestro.ui.theme.CleanRestroTheme
import com.assignment.presentation.constants.Constants

/**
 * To be used when there is an error case.
 *
 * @param modifier to modify the composable.
 * @param errorMessage message to be shown.
 *
 */
@Composable
fun Error(modifier: Modifier = Modifier, errorMessage: String) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp), contentAlignment = Alignment.Center
    )
    {
        Text(
            text = errorMessage,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }

}

@Preview(showSystemUi = true)
@Composable
fun PreviewError() {
    CleanRestroTheme {
        Error(errorMessage = Constants.ERROR_MESSAGE)
    }
}
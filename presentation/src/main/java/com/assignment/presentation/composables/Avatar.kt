package com.assignment.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.assignment.presentation.R

/**
 * To be used where async loading of image is required.
 *
 * @param modifier to modify the composable.
 * @param imageUrl url of the image to load.
 *
 */
@Composable
fun Avatar(modifier: Modifier = Modifier, imageUrl: String) {

    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .error(R.drawable.ic_launcher_background).build(),
        contentDescription = stringResource(id = R.string.character_avatar),
        contentScale = ContentScale.Crop
    )
}
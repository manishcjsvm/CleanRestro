package com.assignment.presentation.navigation

/**
 * Navigation screen for the nav graph.
 *
 * @param route route for the nav screen.
 */
sealed class NavScreens(val route: String) {

    data object CharactersListScreen : NavScreens(route = NavRoutes.CHARACTERS_LIST_ROUTE)
    data object CharacterDetailsScreen : NavScreens(route = NavRoutes.CHARACTER_DETAILS_ROUTE)
}

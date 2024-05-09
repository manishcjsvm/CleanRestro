package com.assignment.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.assignment.presentation.R
import com.assignment.presentation.base.BaseScreen
import com.assignment.presentation.constants.Constants
import com.assignment.presentation.features.detailsscreen.CharacterDetailsScreen
import com.assignment.presentation.features.homescreen.CharactersListScreen

/**
 * To be used for the navigation.
 *
 * @param navHostController navigation controller to control the destinations.
 */
@Composable
fun NavGraph(navHostController: NavHostController) {


    NavHost(
        navController = navHostController,
        startDestination = NavScreens.CharactersListScreen.route
    )
    {
        composable(route = NavScreens.CharactersListScreen.route)
        {
            BaseScreen(title = stringResource(id = R.string.characters_list_screen)) {
                CharactersListScreen()
                {
                    navHostController.navigate("${NavScreens.CharacterDetailsScreen.route}/$it")
                }
            }
        }
        composable(
            route = "${NavScreens.CharacterDetailsScreen.route}/{${Constants.characterId}}",
            arguments = listOf(navArgument(name = Constants.characterId)
            {
                type = NavType.IntType
            })
        )
        { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt(Constants.characterId)?.let {
                BaseScreen(title = stringResource(id = R.string.character_details),
                    backButtonEnabled = true,
                    onBackButtonClick =
                    {
                        navHostController.popBackStack()
                    }) {
                    CharacterDetailsScreen()
                }
            }

        }
    }
}
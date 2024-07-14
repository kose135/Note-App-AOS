package com.jong.compose.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.longlegsdev.note.presentation.screen.add_edit_note.AddEditNoteScreen
import com.longlegsdev.note.presentation.screen.note.NoteScreen
import com.longlegsdev.note.presentation.screen.splash.SplashScreen


@Composable
fun NavGraph(
    navController: NavHostController
) {
    val splashDuration = 700
    val duration = 500

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(
            route = Screen.Splash.route,
            exitTransition = {
                fadeOut(tween(splashDuration))
            },
        ) {
            SplashScreen {
                navController.navigate(Screen.Note.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }

        composable(
            route = Screen.Note.route,
            enterTransition = {
                fadeIn(tween(duration))
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, tween(duration)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, tween(duration)
                )
            }
        ) {
            NoteScreen(navController)
        }

        composable(
            Screen.AddEditNote.route + "/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, tween(duration)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, tween(duration)
                )
            },
        ) {
            AddEditNoteScreen(navController = navController)
        }

    }
}
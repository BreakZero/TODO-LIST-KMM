package com.easy.todolist.android.feature.settings

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.koinViewModel

const val SettingsRoute = "settings"

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(SettingsRoute, navOptions)
}

fun NavGraphBuilder.bindSettingsGraph(
    onLogout:() -> Unit
) {
    composable(route = SettingsRoute) {
        val settingsViewModel: SettingsViewModel = koinViewModel()
        LaunchedEffect(key1 = null) {
            settingsViewModel.eventChannel.collect {
                when(it) {
                    is SettingsEvent.Logout -> onLogout()
                    else -> Unit
                }
            }
        }
        val profileUiState: ProfileUiState by settingsViewModel.profileUiState.collectAsStateWithLifecycle()
        SettingsScreen(uiState = profileUiState, onEvent = settingsViewModel::onEvent)
    }
}
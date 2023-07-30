package com.easy.todolist.android.feature.sign_in

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

const val SignInRoute = "sign_in"

fun NavController.navigateToSignIn(navOptions: NavOptions? = null) {
    this.navigate(SignInRoute, navOptions)
}

fun NavGraphBuilder.bindSignInGraph(
    toSignUp: () -> Unit,
    toTaskList: () -> Unit
) {
    composable(route = SignInRoute) {
        val viewModel: SignInViewModel = koinViewModel()
        val uiState by viewModel.state.collectAsStateWithLifecycle()
        LaunchedEffect(key1 = null) {
            viewModel.eventChannel.collectLatest {
                when (it) {
                    SignInEvent.SignUpClicked -> toSignUp()
                    SignInEvent.OnSignIn -> toTaskList()
                    else -> Unit
                }
            }
        }
        SignInScreen(uiState = uiState, onEvent = viewModel::onEvent)
    }
}
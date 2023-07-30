package com.easy.todolist.android.feature.sign_up

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

const val SignUpRoute = "sign_up"

fun NavController.navigateToSignUp(navOptions: NavOptions? = null) {
    this.navigate(SignUpRoute, navOptions)
}

fun NavGraphBuilder.bindSignUpScreen(
    backToSignIn: () -> Unit,
    forwardTaskList: () -> Unit
) {
    composable(route = SignUpRoute) {
        val viewModel: SignUpViewModel = koinViewModel()
        val uiState by viewModel.state.collectAsStateWithLifecycle()
        LaunchedEffect(key1 = null) {
            viewModel.eventChannel.collectLatest {
                when (it) {
                    SignUpEvent.SignInClicked -> backToSignIn()
                    SignUpEvent.OnSignUp -> forwardTaskList()
                    else -> Unit
                }
            }
        }
        SignUpScreen(uiState = uiState, onEvent = viewModel::onEvent)
    }
}
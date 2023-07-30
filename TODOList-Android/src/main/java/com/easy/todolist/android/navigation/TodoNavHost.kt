package com.easy.todolist.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.easy.todolist.android.feature.detail.bindTaskDetailGraph
import com.easy.todolist.android.feature.detail.navigateToTaskDetail
import com.easy.todolist.android.feature.settings.bindSettingsGraph
import com.easy.todolist.android.feature.settings.navigateToSettings
import com.easy.todolist.android.feature.sign_in.bindSignInGraph
import com.easy.todolist.android.feature.sign_up.bindSignUpScreen
import com.easy.todolist.android.feature.sign_up.navigateToSignUp
import com.easy.todolist.android.feature.todo_list.bindTodoListGraph
import com.easy.todolist.android.feature.todo_list.navigationToList

@Composable
fun TodoNavHost(
    modifier: Modifier = Modifier,
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        bindSignInGraph(
            toSignUp = navController::navigateToSignUp,
            toTaskList = {
                navController.navigationToList(
                    navOptions {
                        this.launchSingleTop = true
                    }
                )
            }
        )
        bindSignUpScreen(
            backToSignIn = navController::popBackStack,
            forwardTaskList = navController::navigationToList
        )
        bindTodoListGraph(
            toSettings = navController::navigateToSettings,
            viewDetail = {
                navController.navigateToTaskDetail(taskId = it)
            }
        )
        bindTaskDetailGraph(
            popBack = navController::popBackStack
        )
        bindSettingsGraph(navController::popBackStack)
    }
}

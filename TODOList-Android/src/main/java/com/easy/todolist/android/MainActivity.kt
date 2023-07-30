package com.easy.todolist.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easy.todolist.android.feature.sign_in.SignInRoute
import com.easy.todolist.android.feature.todo_list.TodoListRoute
import com.easy.todolist.android.navigation.TodoNavHost
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            mainViewModel.mainUIState.value is MainUIState.Loading
        }
        setContent {
            TodoListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val mainUIState by mainViewModel.mainUIState.collectAsStateWithLifecycle()
                    val startDestination =
                        if (mainUIState is MainUIState.Success && (mainUIState as MainUIState.Success).user != null) TodoListRoute
                        else SignInRoute
                    TodoNavHost(startDestination = startDestination)
                }
            }
        }
    }
}

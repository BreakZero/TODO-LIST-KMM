package com.easy.todolist.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easy.todolist.android.feature.sign_in.SignInEvent
import com.easy.todolist.android.feature.sign_in.SignInScreen
import com.easy.todolist.android.feature.sign_in.SignInUIState
import com.easy.todolist.android.feature.sign_in.SignInViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val state by mainViewModel.existUser.collectAsStateWithLifecycle()
                    if (state) {
                        GreetingView(text = "Hello world")
                    } else {
                        val viewModel: SignInViewModel = koinViewModel()
                        val uiState by viewModel.state.collectAsStateWithLifecycle()
                        LaunchedEffect(key1 = null) {
                            viewModel.eventChannel.collectLatest {
                                if (SignInEvent.OnSignupClick == it) {
                                    println("click sign up")
                                }
                            }
                        }
                        SignInScreen(uiState = uiState, onEvent = viewModel::onEvent)
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    TodoListTheme {
        GreetingView("Hello, Android!")
    }
}

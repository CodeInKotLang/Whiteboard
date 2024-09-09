package com.example.whiteboard.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.whiteboard.presentation.theme.WhiteboardTheme
import com.example.whiteboard.presentation.whiteboard.WhiteboardScreen
import com.example.whiteboard.presentation.whiteboard.WhiteboardViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhiteboardTheme {
                val viewModel = viewModel<WhiteboardViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    WhiteboardScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}

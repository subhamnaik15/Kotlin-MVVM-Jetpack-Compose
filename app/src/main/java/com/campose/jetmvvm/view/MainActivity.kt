package com.campose.jetmvvm.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import com.campose.jetmvvm.ui.screen.ApplicationSwitcherScreen
import com.campose.jetmvvm.viewmodel.MainViewModel
import com.campose.jetmvvm.ui.theme.JetMvvmTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * MaiActivity lunch first
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //light and dark theme manage
            JetMvvmTheme {
                CompositionLocalProvider() {
                    //login or home page decide
                    ApplicationSwitcherScreen(viewModel, this@MainActivity)
                }
            }
        }
    }
}
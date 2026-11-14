package com.son.rhym

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.son.rhym.core.navigation.AppRoot
import com.son.rhym.ui.theme.RhymTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RhymTheme {
                RhymTheme { AppRoot() }
            }
        }
    }
}

@Preview
@Composable
fun MainActivityPreview() {
    RhymTheme {
    }
}
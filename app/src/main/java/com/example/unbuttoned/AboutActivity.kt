package com.example.unbuttoned

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.unbuttoned.ui.theme.UnbuttonedTheme
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer


class AboutActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnbuttonedTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LibrariesContainer(
                        Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}


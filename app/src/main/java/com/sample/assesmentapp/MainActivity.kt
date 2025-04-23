package com.sample.assesmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.sample.assesmentapp.ui.theme.AssesmentAppTheme
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssesmentAppTheme {
                AppNavGraph()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AssesmentAppTheme {
        AppNavGraph()
    }
}
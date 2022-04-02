package io.king.postapi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.king.postapi.ui.theme.PostApiTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel : MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        setContent {
            PostApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Forms(
                        viewModel
                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    PostApiTheme {
//        Forms(vie)
//    }
//}
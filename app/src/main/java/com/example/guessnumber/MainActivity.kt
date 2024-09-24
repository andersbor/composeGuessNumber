package com.example.guessnumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.guessnumber.ui.theme.GuessNumberTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessNumberTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NumberGuess(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NumberGuess(modifier: Modifier = Modifier) {
    var numberStr by remember { mutableStateOf("") }
    val secretNumber = remember { (1..100).random() }
    Column(modifier = modifier) {
        var message by remember { mutableStateOf("") }
        Text("Guess a number between 1 and 100")
        Row(verticalAlignment = CenterVertically) {
            OutlinedTextField(
                value = numberStr,
                onValueChange = { numberStr = it },
                label = { Text("Enter a number") },
                modifier = modifier
            )
            Button(onClick = {
                val number = numberStr.toIntOrNull()
                if (number == null) {
                    // TODO error message
                } else {
                    if (number < secretNumber) {
                        message = "Too low"
                    } else if (number > secretNumber) {
                        message = "Too high"
                    } else {
                        message = "You guessed it!"
                    }
                }
            }) {
                Text("Guess")
            }
        }
        Text(text = message)
    }
}

@Preview(showBackground = true)
@Composable
fun NumberGuessPreview() {
    GuessNumberTheme {
        NumberGuess()
    }
}
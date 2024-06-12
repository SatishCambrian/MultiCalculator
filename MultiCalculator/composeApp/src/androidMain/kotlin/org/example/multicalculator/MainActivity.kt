package org.example.multicalculator

//import App
import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //App()
            CalcView()
        }
    }
}

/*@Preview
@Composable
fun AppAndroidPreview() {
    App()
}*/
@Preview
@Composable
fun CalcView(){

}

@Composable
fun CalcRow(){

}

@Composable
fun CalcDisplay(display: MutableState<String>) {
    Text(
        text = display.value,
        fontSize = 30.sp,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth()
    )
}

@Composable
fun CalcNumericButton(number: Int, display: MutableState<String>) {
    Button(
        onClick = {
            if (display.value == "0") {
                display.value = number.toString()
            } else {
                display.value += number.toString()
            }
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString(), fontSize = 20.sp)
    }
}

@Composable
fun CalcOperationButton(operation: String, display: MutableState<String>) {
    Button(
        onClick = {
            // Add functionality for operation button click
        },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation, fontSize = 20.sp)
    }
}
@Composable
fun CalcEqualsButton(display: MutableState<String>) {
    Button(
        onClick = { display.value = "0" },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=", fontSize = 20.sp)
    }
}




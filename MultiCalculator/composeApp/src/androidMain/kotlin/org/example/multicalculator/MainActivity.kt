package org.example.multicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalcView()
        }
    }
}

@Preview
@Composable
fun CalcView() {
    //define state variables to hold the numbers, operation and display text
    var leftNumber by rememberSaveable { mutableStateOf(0) }
    var rightNumber by rememberSaveable { mutableStateOf(0) }
    var operation by rememberSaveable { mutableStateOf("") }
    var complete by rememberSaveable { mutableStateOf(false) }
    var displayText by rememberSaveable { mutableStateOf("0") }

    //result calculation based on selected operation
    if (complete && operation.isNotEmpty()) {
        var answer = 0
        when (operation) {
            "+" -> answer = leftNumber + rightNumber
            "-" -> answer = leftNumber - rightNumber
            "*" -> answer = leftNumber * rightNumber
            "/" -> if (rightNumber != 0) answer = leftNumber / rightNumber
        }
        displayText = answer.toString()
    } else if (operation.isNotEmpty() && !complete) {
        displayText = rightNumber.toString()
    } else {
        displayText = leftNumber.toString()
    }
    // function to handle number button press
    fun numberPress(btnNum: Int) {
        if (complete) {//Reset if calculation is complete
            leftNumber = 0
            rightNumber = 0
            operation = ""
            complete = false
        }
        if (operation.isNotBlank() && !complete) {
            rightNumber = rightNumber * 10 + btnNum
        } else if (operation.isBlank() && !complete) {
            leftNumber = leftNumber * 10 + btnNum
        }
    }
    //function to handle operation button press
    fun operationPress(op: String) {
        if (!complete) {
            operation = op
        }
    }
    //function to handle equals button press
    fun equalsPress() {
        complete = true
    }
    // UI for Calculator
    Column(modifier = Modifier.background(Color.LightGray)) {
        Row {
            CalcDisplay(displayText)
        }
        Row {
            Column {
                for (i in 7 downTo 1 step 3) {
                    CalcRow(onPress = { number -> numberPress(number) }, startNum = i, numButtons = 3)
                }
                Row {
                    CalcNumericButton(onPress = { number -> numberPress(number) }, number = 0)
                    CalcEqualsButton(onPress = { equalsPress() })
                }
            }
            Column {
                CalcOperationButton(onPress = { op -> operationPress(op) }, operation = "+")
                CalcOperationButton(onPress = { op -> operationPress(op) }, operation = "-")
                CalcOperationButton(onPress = { op -> operationPress(op) }, operation = "*")
                CalcOperationButton(onPress = { op -> operationPress(op) }, operation = "/")
            }
        }
    }
}

//Function to create a row of number buttons
@Composable
fun CalcRow(onPress: (number: Int) -> Unit, startNum: Int, numButtons: Int) {
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) {
        for (i in startNum until endNum) {
            CalcNumericButton(onPress = onPress, number = i)
        }
    }
}

@Composable
fun CalcDisplay(display: String) {
    Text(
        text = display,
        fontSize = 30.sp,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth()
    )
}

@Composable
fun CalcNumericButton(onPress: (number: Int) -> Unit, number: Int) {
    Button(
        onClick = { onPress(number) },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString(), fontSize = 20.sp)
    }
}

@Composable
fun CalcOperationButton(onPress: (operation: String) -> Unit, operation: String) {
    Button(
        onClick = { onPress(operation) },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation, fontSize = 20.sp)
    }
}

@Composable
fun CalcEqualsButton(onPress: () -> Unit) {
    Button(
        onClick = { onPress() },
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = "=", fontSize = 20.sp)
    }
}

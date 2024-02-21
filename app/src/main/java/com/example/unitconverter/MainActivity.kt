package com.example.unitconverter

import android.os.Bundle
import kotlin.math.roundToInt
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    UnitConverter()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Metres") }
    var outputUnit by remember { mutableStateOf("Metres") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.0) }
    val oconversionFactor = remember { mutableStateOf(1.0) }
    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?:0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oconversionFactor.value).roundToInt() / 100.0
    outputValue = result.toString()
    }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    , horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier= Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
        } ,
            label = { Text("Enter value")})
        Spacer(modifier= Modifier.height(16.dp))
        Row {
        Box {
            Button(onClick = {iExpanded = true}) {
                Text(text = inputUnit)
         Icon(Icons.Default.ArrowDropDown,
        contentDescription = "Click me")
                
            }
                DropdownMenu(expanded = iExpanded,
                    onDismissRequest = {iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimetres")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimetres"
                            conversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Metres")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Metres"
                            conversionFactor.value = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text(text ="Milimetres")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milimetres"
                            conversionFactor.value = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })

                }
            }

            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = {oExpanded =true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Click me")

                }

                    DropdownMenu(expanded = oExpanded,
                        onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(text = { Text(text = "Centimetres")},
                            onClick = {
                                oExpanded = false
                                outputUnit = "Centimetres"
                                oconversionFactor.value = 0.01
                                convertUnits()
                            })
                        DropdownMenuItem(text = { Text(text = "Metres")},
                            onClick = {
                                oExpanded = false
                                outputUnit = "Metres"
                                oconversionFactor.value = 1.0
                                convertUnits()
                            })
                        DropdownMenuItem(text = { Text(text ="Milimetres")},
                            onClick = {
                                oExpanded = false
                                outputUnit = "Milimetres"
                                oconversionFactor.value = 0.001
                                convertUnits()
                            })
                        DropdownMenuItem(text = { Text(text = "Feet")},
                            onClick = {
                                oExpanded = false
                                outputUnit = "Feet"
                                oconversionFactor.value = 0.3048
                                convertUnits()
                            })

                    }
                }
            }
        Spacer(modifier= Modifier.height(16.dp))
        Text(text = "Result: ${outputValue} ${outputUnit}",
            style = MaterialTheme.typography.headlineMedium)
        }

    }


@Preview (showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}

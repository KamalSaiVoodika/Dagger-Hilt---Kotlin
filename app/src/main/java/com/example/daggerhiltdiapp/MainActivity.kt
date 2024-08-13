package com.example.daggerhiltdiapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.daggerhiltdiapp.ui.theme.DaggerHiltDIAppTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DaggerHiltDIAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 1:: without DI
                    /*
                    val classC = ClassC()
                     classC.startClassC()
                     */


                    // 2 :: Data Injection
                    /*
                     val classC = ClassC()
                     classC.classA = ClassA()
                     classC.classB = ClassB()
                      classC.startClassC()
                      */


                    // 3:: Constructor Injection
                    /*
                   val classA = ClassA()
                    val classB = ClassB()
                    val  classC = ClassC(classA,classB)
                    classC.startClassC()
                    */


                    // 4:: Dagger
                   DaggerClassCComponent.create().getClassCInstance().startClassC()

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DaggerHiltDIAppTheme {
        Greeting("Android")
    }
}


class Coffee {
    fun makeCoffee() {
        Log.d("TAG", "makeCoffee is performed")
    }
}
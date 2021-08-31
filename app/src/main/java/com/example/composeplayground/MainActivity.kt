package com.example.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                DesignLayout()
            }
        }
    }
}

@Composable
fun MyApp(content:@Composable () -> Unit){
    Surface(color = Color.Yellow) {
        content()
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

    Text(text = "Hello $name!",
        modifier = Modifier.padding(24.dp).background(color=backgroundColor).clickable(onClick = {isSelected = !isSelected}),
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun DesignLayout(names:List<String> = List(1000){"Android #$it"}){
    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names = names, modifier = Modifier.weight(1f))
        Divider(color = Color.Transparent,thickness = 32.dp)
        Counter()
    }
}

@Composable
fun Counter(){
    val count = remember { mutableStateOf(0)    }
    Button(onClick = { count.value++ }) {
        Text(text = "I have been clicked ${count.value} times")
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier){
    LazyColumn(modifier = modifier){
        items(items = names){ name->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp{
        DesignLayout()
    }
}
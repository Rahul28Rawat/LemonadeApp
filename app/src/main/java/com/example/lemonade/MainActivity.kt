package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageWithTheText(
    modifier:Modifier= Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
) {
    var stepNumber by remember {
        mutableStateOf(1)
    }
    var count by remember {
        mutableStateOf(1)
    }
    val picNumber=when(stepNumber){
        1->R.drawable.lemon_tree
        2->R.drawable.lemon_squeeze
        3->R.drawable.lemon_drink
        else->R.drawable.lemon_restart
    }
    val picValue=when(stepNumber){
        1->R.string.pic_1
        2->R.string.pic_2
        3->R.string.pic_3
        else->R.string.pic_4
    }
    val textNumber=when(stepNumber){
        1->R.string.step_1
        2->R.string.step_2
        3->R.string.step_3
        else->R.string.step_4
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ){
        Column(
            modifier=modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = picNumber),
                contentDescription = stringResource(id = picValue),

                modifier= Modifier
                    .height(300.dp)
                    .width(250.dp)
                    .clickable {
                        if (stepNumber == 1) {
                            stepNumber = 2
                            count = (2..4).random()
                        } else if (stepNumber == 2) {
                            if (count == 0) {
                                stepNumber = 3
                            } else {
                                count--
                            }
                        } else if (stepNumber == 3) {
                            stepNumber = 4
                        } else {
                            stepNumber = 1
                        }
                    }
            )
//        Button(modifier= Modifier
//            .height(300.dp)
//            .width(250.dp),
//        onClick = {
//            if(stepNumber==1){
//                stepNumber=2
//                count=(2..4).random()
//            }
//            else if (stepNumber==2){
//                if(count==0){
//                    stepNumber=3
//                }
//                else{
//                    count--;
//                }
//            }
//            else if(stepNumber==3){
//                stepNumber=4
//            }
//            else{
//                stepNumber=1
//            }
//        }) {
//            Image(
//                painter = painterResource(id = picNumber),
//                contentDescription = stringResource(id = picValue)
//            )
//        }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = textNumber),
                fontSize = 18.sp
            )
        }
    }


}

@Preview
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        ImageWithTheText()
    }
}
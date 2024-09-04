package com.example.laundryapp.ui.theme.Screens.Details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.laundryapp.R
import com.example.laundryapp.data.AuthViewModel
import com.example.laundryapp.navigation.ROUTE_LOGIN

@Composable
fun Greeting(name: String, navController: NavController){
    Box (){
        Image(painter = painterResource(id = R.drawable.kovyrina),
            contentDescription ="dashboard background",
            contentScale = ContentScale.FillBounds)
}

    var fullNames by remember { mutableStateOf(value = "")

    }
    var phoneNumber by remember {
        mutableStateOf(value = "")
    }
    var email by remember {
        mutableStateOf(value = "")
    }
    var password by remember {
        mutableStateOf(value = "")
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center

    ) { Spacer(modifier = Modifier.height(10.dp))
        Text(text = "REGISTER YOUR DETAILS",
            fontSize = 30.sp,
            color = Color.Cyan,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Transparent)
                .padding(vertical = 50.dp)
                .align(alignment = Alignment.Start)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier
            .height(25.dp))
        OutlinedTextField(modifier = Modifier
            .wrapContentWidth()
            .align(Alignment.CenterHorizontally),
            label = { Text(text = "Enter Full Names") },
            placeholder = { Text(text = "Full Names") },
            value = fullNames,
            onValueChange ={
                    newName -> fullNames = newName
            } )
        Spacer(modifier = Modifier
            .height(25.dp))
        OutlinedTextField(modifier = Modifier
            .wrapContentWidth()
            .align(Alignment.CenterHorizontally),
            label = { Text(text = "phone Number") },
            placeholder = { Text(text = "phoneNumber") },
            value = phoneNumber,
            onValueChange ={
                    newName -> phoneNumber = newName
            } )
        Spacer(modifier = Modifier
            .height(25.dp))
        OutlinedTextField(modifier = Modifier
            .wrapContentWidth()
            .align(Alignment.CenterHorizontally),
            label = { Text(text = "Insert Email") },
            placeholder = { Text(text = "Insert Email") },
            value = email,
            onValueChange ={
                    newName -> email = newName
            } )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(modifier = Modifier
            .wrapContentWidth()
            .align(Alignment.CenterHorizontally),
            
            label = { Text(text = "Enter Password") },
            placeholder = { Text(text = "Please Enter Password ") },
            value = password,
            onValueChange ={
                    newPassword->password=newPassword
            } )
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = {
                val register =AuthViewModel(navController, context)
                register.signup(fullNames.trim(),phoneNumber.trim(),email.trim(),
                    password.trim())
                navController.navigate(ROUTE_LOGIN)
            },
            colors = ButtonDefaults.buttonColors(Color.Cyan),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(modifier = Modifier.padding(10.dp),
                text = "REGISTER HERE")
        }
        Spacer(modifier = Modifier.height(10.dp))


        }

    }



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview(){
    Greeting(name = "ADEBAH", rememberNavController())
}
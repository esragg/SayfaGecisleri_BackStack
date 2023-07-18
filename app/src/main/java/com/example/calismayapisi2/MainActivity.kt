package com.example.calismayapisi2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.calismayapisi2.ui.theme.CalismaYapisi2Theme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalismaYapisi2Theme { //tema
                // A surface container using the 'background' color from the theme robl
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SayfaGecisleri()
                }
            }
        }
    }
}
@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa") {
        composable("anasayfa") {
            AnaSayfa(navController = navController)
        }
        composable("sayfa_a/{nesne}", //veri alma kismi
            arguments = listOf(
                navArgument("nesne") { type = NavType.StringType }
                )
            ) {
                val json = it.arguments?.getString("nesne")!!
                val nesne = Gson().fromJson(json,Kisiler::class.java) //Kisiler turunden nesneye donusturduk
            SayfaA(navController = navController,nesne) //nesne olarak ilettik
        }
        composable("sayfa_b") {
            SayfaB()
        }
    }
}
@Composable
fun AnaSayfa(navController: NavController) {
    val sayac = remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "AnaSayfa", fontSize = 50.sp)
        Button(onClick = {
            val kisi = Kisiler("ahmet",18,1.78f,true)

            val kisiJson = Gson().toJson(kisi) //nesnemi String'lestirdim

            navController.navigate("sayfa_a/$kisiJson") //nesnemi String olarak gonderiyorum
        }) {
            Text(text = "Sayfa A'ya Git")

        }
        Text(text = "Sayac: ${sayac.value}")

        Button(onClick = {
            sayac.value += 1
        }) {
            Text(text = "Tikla")
        }
    }

    LaunchedEffect(key1 = true) {
        Log.e("Anasayfa","LaunchedEffect calisti")
    }
    SideEffect {
        Log.e("Anasayfa","SideEffect calisti")
    }
    DisposableEffect(Unit){
        onDispose {
            Log.e("Anasayfa","DisposableEffect calisti")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnaSayfaPreview() {
    CalismaYapisi2Theme {

    }
}
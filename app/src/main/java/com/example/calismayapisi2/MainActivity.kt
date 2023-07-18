package com.example.calismayapisi2

import android.os.Bundle
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
        composable("sayfa_a/{isim}/{yas}/{boy}/{bekarMi}", //veri alma kismi
            arguments = listOf(
                navArgument("isim") { type = NavType.StringType },
                navArgument("yas") { type = NavType.IntType },
                navArgument("boy") { type = NavType.FloatType },
                navArgument("bekarMi") { type = NavType.BoolType}
                )
            ) {
                val isim = it.arguments?.getString("isim")!!
                val yas = it.arguments?.getInt("yas")!!
                val boy = it.arguments?.getFloat("boy")!!
                val bekarMi = it.arguments?.getBoolean("bekarMi")!!
            SayfaA(navController = navController,isim,yas,boy,bekarMi)
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
            navController.navigate("sayfa_a/ahmet/18/1.78f/true") //veri gonderme kismi
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
}

@Preview(showBackground = true)
@Composable
fun AnaSayfaPreview() {
    CalismaYapisi2Theme {

    }
}
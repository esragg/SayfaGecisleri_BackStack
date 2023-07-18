package com.example.calismayapisi2

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp

@Composable
fun SayfaB() {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sayfa B", fontSize = 50.sp)
    }

    val activity = (LocalContext.current as Activity)
    BackHandler(onBack = { //artik geri tusuna bastigimizda burada ne istiyorsak onu yapacak
        Log.e("SayfaB", "Geri tusuna basildi")
        activity.finish()
    })
}
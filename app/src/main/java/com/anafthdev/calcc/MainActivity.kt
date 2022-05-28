package com.anafthdev.calcc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.anafthdev.calcc.ui.main.MainScreen
import com.anafthdev.calcc.ui.theme.CalcCTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			CalcCTheme {
				Surface(
					color = MaterialTheme.colorScheme.background
				) {
					MainScreen()
				}
			}
		}
	}
}

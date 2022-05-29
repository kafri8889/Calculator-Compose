package com.anafthdev.calcc.foundation.extension

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun Any?.toast(context: Context, length: Int = Toast.LENGTH_SHORT) =
	Toast.makeText(context, this.toString(), length).show()

@SuppressLint("ComposableNaming")
@Composable
fun Any?.toast(length: Int = Toast.LENGTH_SHORT) =
	Toast.makeText(LocalContext.current, this.toString(), length).show()

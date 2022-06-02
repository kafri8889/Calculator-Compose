package com.anafthdev.calcc.data

object Operations {
	
	val LOG_10 = "log" to "log10"
	
	val DECIMAL = "," to "."
	
	val PERCENT = "%" to "!|"
	
	val DIVISION = "÷" to "/"
	
	val MULTIPLICATION = "x" to "*"
	
	val InvSin = "ˠ" to "asin"
	
	val InvCos = "〥" to "acos"
	
	val InvTan = "ˠ" to "atan"
	
	val values = listOf(
		LOG_10,
		DECIMAL,
		PERCENT,
		DIVISION,
		MULTIPLICATION,
		InvSin,
		InvCos,
		InvTan
	)
}
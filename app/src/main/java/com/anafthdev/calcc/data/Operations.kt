package com.anafthdev.calcc.data

object Operations {
	
	val LOG_10 = "log" to "log10"
	
	val DECIMAL = "," to "."
	
	val PERCENT = "%" to "!|"
	
	val DIVISION = "÷" to "/"
	
	val MULTIPLICATION = "x" to "*"
	
	val values = listOf(
		LOG_10,
		DECIMAL,
		PERCENT,
		DIVISION,
		MULTIPLICATION
	)
}
package com.anafthdev.calcc.data

object Operations {
	
	val LOG_10 = "log" to "log10"
	
	val DECIMAL = "," to "."
	
	val PERCENT = "%" to "!|"
	
	val DIVISION = "÷" to "/"
	
	val MULTIPLICATION = "x" to "*"
	
	val INVERSE_SIN = "sin-1" to "asin"
	
	val INVERSE_COS = "cos-1" to "acos"
	
	val INVERSE_TAN = "tan-1" to "atan"
	
	val values = listOf(
		LOG_10,
		DECIMAL,
		PERCENT,
		DIVISION,
		MULTIPLICATION,
		INVERSE_SIN,
		INVERSE_COS,
		INVERSE_TAN
	)
}
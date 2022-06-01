package com.anafthdev.calcc.data

object Operations {
	
	val DECIMAL = "," to "."
	
	val PERCENT = "%" to "!|"
	
	val DIVISION = "÷" to "/"
	
	val MULTIPLICATION = "x" to "*"
	
	val INVERSE_SIN = "sin-1" to "asin"
	
	val INVERSE_COS = "cos-1" to "acos"
	
	val INVERSE_TAN = "tan-1" to "atan"
	
	val values = listOf(
		DECIMAL,
		PERCENT,
		DIVISION,
		MULTIPLICATION,
		INVERSE_SIN,
		INVERSE_COS,
		INVERSE_TAN
	)
}
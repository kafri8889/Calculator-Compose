package com.anafthdev.calcc.data

object Operations {
	
	val SQRT = "√" to "|~"
	
	val DECIMAL = "," to "."
	
	val PERCENT = "%" to "!|"
	
	val DIVISION = "÷" to "/"
	
	val MULTIPLICATION = "x" to "*"
	
	val values = listOf(
		SQRT,
		DECIMAL,
		PERCENT,
		DIVISION,
		MULTIPLICATION
	)
}
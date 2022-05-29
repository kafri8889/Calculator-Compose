package com.anafthdev.calcc.data

object Operations {
	
	val DECIMAL = "," to "."
	
	val PERCENT = "%" to "!|"
	
	val DIVISION = "÷" to "/"
	
	val MULTIPLICATION = "x" to "*"
	
	val values = listOf(
		DECIMAL,
		PERCENT,
		DIVISION,
		MULTIPLICATION
	)
}
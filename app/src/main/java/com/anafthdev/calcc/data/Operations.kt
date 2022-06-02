package com.anafthdev.calcc.data

object Operations {
	
	val LOG_10 = "log" to "log10"
	
	val DECIMAL = "," to "."
	
	val PERCENT = "%" to "!|"
	
	val DIVISION = "÷" to "/"
	
	val MULTIPLICATION = "x" to "*"
	
	val INV_SIN = "ˠ" to "asin"
	
	val INV_COS = "〥" to "acos"
	
	val INV_TAN = "ˁ" to "atan"
	
	val INV_SIN_DEGREES = "¤" to "ASIN"
	
	val INV_COS_DEGREES = "≭" to "ACOS"
	
	val INV_TAN_DEGREES = "⥿" to "ATAN"
	
	val SIN_DEGREES = "sin" to "Sin"
	
	val COS_DEGREES = "cos" to "Cos"
	
	val TAN_DEGREES = "tan" to "Tan"
	
	val values = listOf(
		LOG_10,
		DECIMAL,
		PERCENT,
		DIVISION,
		MULTIPLICATION,
		INV_SIN,
		INV_COS,
		INV_TAN,
		INV_SIN_DEGREES,
		INV_COS_DEGREES,
		INV_TAN_DEGREES,
		SIN_DEGREES,
		COS_DEGREES,
		TAN_DEGREES
	)
}
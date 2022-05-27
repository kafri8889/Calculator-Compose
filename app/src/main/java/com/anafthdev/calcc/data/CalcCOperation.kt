package com.anafthdev.calcc.data

sealed class CalcCOperation {
	data class Div(
		override val symbol: String = "÷",
		val operatorSymbol: String = "/"
	): CalcCOperation(), Calc
	
	data class Mul(
		override val symbol: String = "x",
		val operatorSymbol: String = "/"
	): CalcCOperation(), Calc
	
	data class Add(override val symbol: String = "+"): CalcCOperation(), Calc
	
	data class Sub(override val symbol: String = "-"): CalcCOperation(), Calc
}
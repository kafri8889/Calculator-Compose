package com.anafthdev.calcc.data

sealed class CalcCOperation {
	
	data class Pi(override val symbol: String = "π"): CalcCOperation(), Calc
	
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
	
	data class Sin(override val symbol: String = "sin"): CalcCOperation(), Calc
	
	data class Cos(override val symbol: String = "cos"): CalcCOperation(), Calc
	
	data class Tan(override val symbol: String = "tan"): CalcCOperation(), Calc
	
	data class Log(override val symbol: String = "log"): CalcCOperation(), Calc
	
	data class Pow(override val symbol: String = "^"): CalcCOperation(), Calc
	
	data class Exp(override val symbol: String = "e"): CalcCOperation(), Calc
	
	data class Sqrt(override val symbol: String = "√"): CalcCOperation(), Calc
	
	data class Factorial(override val symbol: String = "!"): CalcCOperation(), Calc
	
	data class NaturalLogarithm(override val symbol: String = "ln"): CalcCOperation(), Calc
	
	
	
	data class ArcSin(
		override val symbol: String = "sin -1",
		val operatorSymbol: String = "asin"
	): CalcCOperation(), Calc
	
	data class ArcCos(
		override val symbol: String = "cos -1",
		val operatorSymbol: String = "acos"
	): CalcCOperation(), Calc
	
	data class ArcTan(
		override val symbol: String = "tan -1",
		val operatorSymbol: String = "atan"
	): CalcCOperation(), Calc
	
	data class InvLog(
		override val symbol: String = "10 x",
		val operatorSymbol: String = "10^"
	): CalcCOperation(), Calc
	
	data class InvSqrt(
		override val symbol: String = "x 2",
		val operatorSymbol: String = "#"
	): CalcCOperation(), Calc
	
	data class InvNaturalLogarithm(
		override val symbol: String = "e x",
		val operatorSymbol: String = "e^"
	): CalcCOperation(), Calc
	
}
package com.anafthdev.calcc.data

sealed class CalcCAdvancedButton {
	
	data class Rad(override val symbol: String = "RAD"): CalcCAdvancedButton(), Calc
	
	data class Deg(override val symbol: String = "DEG"): CalcCAdvancedButton(), Calc
	
	data class Inverse(override val symbol: String = "INV"): CalcCAdvancedButton(), Calc
	
	data class OpenParenthesis(override val symbol: String = "("): CalcCAdvancedButton(), Calc
	
	data class CloseParenthesis(override val symbol: String = ")"): CalcCAdvancedButton(), Calc
	
}
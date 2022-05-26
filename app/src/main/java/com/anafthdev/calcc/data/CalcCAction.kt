package com.anafthdev.calcc.data

sealed class CalcCAction {

	data class Clear(override val symbol: String = "C"): CalcCAction(), Calc
	data class Percent(override val symbol: String = "%"): CalcCAction(), Calc
	data class Decimal(override val symbol: String = ","): CalcCAction(), Calc
	data class Delete(override val symbol: String = "<-"): CalcCAction(), Calc
	data class Calculate(override val symbol: String = "="): CalcCAction(), Calc
}
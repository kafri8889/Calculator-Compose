package com.anafthdev.calcc.data

sealed class CalcCNumber {
	data class DoubleZero(override val symbol: String = "00"): CalcCNumber(), Calc
	data class Zero(override val symbol: String = "0"): CalcCNumber(), Calc
	data class One(override val symbol: String = "1"): CalcCNumber(), Calc
	data class Two(override val symbol: String = "2"): CalcCNumber(), Calc
	data class Three(override val symbol: String = "3"): CalcCNumber(), Calc
	data class Four(override val symbol: String = "4"): CalcCNumber(), Calc
	data class Five(override val symbol: String = "5"): CalcCNumber(), Calc
	data class Six(override val symbol: String = "6"): CalcCNumber(), Calc
	data class Seven(override val symbol: String = "7"): CalcCNumber(), Calc
	data class Eight(override val symbol: String = "8"): CalcCNumber(), Calc
	data class Nine(override val symbol: String = "9"): CalcCNumber(), Calc
}
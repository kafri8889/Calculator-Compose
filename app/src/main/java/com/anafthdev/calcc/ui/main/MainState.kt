package com.anafthdev.calcc.ui.main

data class MainState(
	val expression: String = "",
	val calculationResult: String = "",
	val isInverse: Boolean = false,
	val useDeg: Boolean = true,
)

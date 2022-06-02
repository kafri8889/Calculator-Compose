package com.anafthdev.calcc.ui.main

sealed class MainAction {
	data class UpdateExpression(val expression: String): MainAction()
	data class UpdateUseDegOrRad(val useDeg: Boolean): MainAction()
}

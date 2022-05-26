package com.anafthdev.calcc.ui.main

sealed class MainAction {
	object Calculate: MainAction()
	data class UpdateExpression(val expression: String): MainAction()
}

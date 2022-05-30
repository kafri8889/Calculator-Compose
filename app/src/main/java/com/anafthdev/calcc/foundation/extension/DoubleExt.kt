package com.anafthdev.calcc.foundation.extension

fun Double.isInt(): Boolean {
	return (this % 1.0) == 0.0
}

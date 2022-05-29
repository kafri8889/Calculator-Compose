package com.anafthdev.calcc.foundation.window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

val LocalComponentSize: ProvidableCompositionLocal<ComponentSize> = compositionLocalOf {
	ComponentSizeMDPI()
}

interface ComponentSize {
	val calcCButton: DpSize
}

class ComponentSizeLDPI(
	override val calcCButton: DpSize = DpSize(44.dp, 44.dp)
): ComponentSize

class ComponentSizeMDPI(
	override val calcCButton: DpSize = DpSize(48.dp, 48.dp)
): ComponentSize

class ComponentSizeHDPI(
	override val calcCButton: DpSize = DpSize(52.dp, 52.dp)
): ComponentSize

class ComponentSizeXHDPI(
	override val calcCButton: DpSize = DpSize(56.dp, 56.dp)
): ComponentSize

class ComponentSizeXXHDPI(
	override val calcCButton: DpSize = DpSize(60.dp, 60.dp)
): ComponentSize

class ComponentSizeXXXHDPI(
	override val calcCButton: DpSize = DpSize(64.dp, 64.dp)
): ComponentSize

@Composable
fun getComponentSize(): ComponentSize {
	return when (DPI.getDPI(LocalDensity.current.density)) {
		DPI.LDPI -> ComponentSizeLDPI()
		DPI.MDPI -> ComponentSizeMDPI()
		DPI.HDPI -> ComponentSizeHDPI()
		DPI.XHDPI -> ComponentSizeXHDPI()
		DPI.XXHDPI -> ComponentSizeXXHDPI()
		DPI.XXXHDPI -> ComponentSizeXXXHDPI()
	}
}

sealed class DPI(val density: Float) {
	object LDPI: DPI(0.75f)
	object MDPI: DPI(1f)
	object HDPI: DPI(1.5f)
	object XHDPI: DPI(2f)
	object XXHDPI: DPI(3f)
	object XXXHDPI: DPI(4f)
	
	companion object {
		fun getDPI(density: Float): DPI {
			return when (density) {
				in 0f..0.75f -> LDPI
				in 0.71f..1f -> MDPI
				in 1.1f..1.5f -> HDPI
				in 1.51f..2f -> XHDPI
				in 2.1f..3f -> XXHDPI
				in 3.1f..4f -> XXXHDPI
				else -> MDPI
			}
		}
	}
}

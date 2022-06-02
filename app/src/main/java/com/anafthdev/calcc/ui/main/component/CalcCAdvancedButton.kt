package com.anafthdev.calcc.ui.main.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anafthdev.calcc.data.Calc
import com.anafthdev.calcc.data.CalcCAdvancedButton
import com.anafthdev.calcc.foundation.extension.split
import com.anafthdev.calcc.foundation.extension.toast
import com.anafthdev.calcc.ui.theme.fully_rounded
import com.anafthdev.calcc.ui.theme.superscriptSpanStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcCAdvancedButton(
	isInverse: Boolean,
	useDeg: Boolean,
	onUpdateExpression: (Calc) -> Unit,
	onUseDegOrRad: (Boolean) -> Unit
) {
	
	com.anafthdev.calcc.data.CalcCButtons.advancedButtons.split(5).second.forEachIndexed { i, list ->
		
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Center,
			modifier = Modifier
				.fillMaxWidth()
		) {
			list.forEach { pair ->
				val calc = if (isInverse) pair!!.second else pair!!.first
				val isDegOrRad = (calc is CalcCAdvancedButton.Rad) or (calc is CalcCAdvancedButton.Deg)
				val interactionSource = remember { MutableInteractionSource() }
				val updateExpression = { onUpdateExpression(calc) }
				
				AdvanceButton(
					calc = calc,
					useDeg = useDeg,
					isDegOrRad = isDegOrRad,
					interactionSource = interactionSource,
					onClick = {
						if (isDegOrRad) {
							onUseDegOrRad(calc is CalcCAdvancedButton.Deg)
						} else updateExpression()
					},
					modifier = Modifier
						.weight(1f)
				)
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AdvanceButton(
	calc: Calc,
	useDeg: Boolean,
	isDegOrRad: Boolean,
	interactionSource: MutableInteractionSource,
	modifier: Modifier = Modifier,
	onClick: () -> Unit
) {
	
	// ex: "sin -1" => ["sin", "-1"]
	val calcSymbol = calc.symbol.split(" ")
	
	val isDeg = (calc is CalcCAdvancedButton.Deg) and useDeg
	val isRad = (calc is CalcCAdvancedButton.Rad) and !useDeg
	
	Box(
		contentAlignment = Alignment.Center,
		modifier = modifier
			.clickable(
				enabled = true,
				indication = null,
				interactionSource = interactionSource,
				onClick = onClick
			)
	) {
		Card(
			shape = fully_rounded,
			interactionSource = interactionSource,
			border = BorderStroke(
				width = 2.dp,
				color = if (isDeg or isRad) MaterialTheme.colorScheme.outline else Color.Transparent
			),
			colors = CardDefaults.cardColors(
				containerColor = if (isDegOrRad) {
					MaterialTheme.colorScheme.onSurface
				} else CardDefaults.cardColors().containerColor(enabled = true).value
			),
			onClick = onClick,
			modifier = Modifier
				.padding(4.dp)
				.aspectRatio(3f / 1.2f)
		) {
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.fillMaxSize()
			) {
				Text(
					text = buildAnnotatedString {
						append(calcSymbol[0])
						if (calcSymbol.size > 1) {
							withStyle(superscriptSpanStyle) {
								append(calcSymbol[1])
							}
						}
					},
					textAlign = TextAlign.Center,
					style = MaterialTheme.typography.bodyMedium.copy(
						color = if (isDegOrRad) MaterialTheme.colorScheme.surfaceVariant
						else MaterialTheme.colorScheme.onSurfaceVariant,
						fontWeight = FontWeight.Medium
					)
				)
			}
		}
	}
}

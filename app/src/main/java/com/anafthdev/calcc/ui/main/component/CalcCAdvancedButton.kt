package com.anafthdev.calcc.ui.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import com.anafthdev.calcc.foundation.extension.split
import com.anafthdev.calcc.ui.theme.fully_rounded

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcCAdvancedButton(
	isInverse: Boolean,
	onUpdateExpression: (Calc) -> Unit
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
				val interactionSource = remember { MutableInteractionSource() }
				val updateExpression = { onUpdateExpression(calc) }
				
				AdvanceButton(
					calc = calc,
					interactionSource = interactionSource,
					onClick = updateExpression,
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
	interactionSource: MutableInteractionSource,
	modifier: Modifier = Modifier,
	onClick: () -> Unit
) {
	
	val calcSymbol = calc.symbol.split(" ")
	
	val superscript = SpanStyle(
		baselineShift = BaselineShift.Superscript,
		fontSize = MaterialTheme.typography.labelSmall.fontSize,
		color = MaterialTheme.typography.bodyMedium.color
	)
	
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
				// TODO: rad deg
				Text(
					text = buildAnnotatedString {
						append(calcSymbol[0])
						if (calcSymbol.size > 1) {
							withStyle(superscript) {
								append(calcSymbol[1])
							}
						}
					},
					textAlign = TextAlign.Center,
					style = MaterialTheme.typography.bodyMedium.copy(
						fontWeight = FontWeight.Medium,
					)
				)
			}
		}
	}
}

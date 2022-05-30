package com.anafthdev.calcc.ui.main.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anafthdev.calcc.R
import com.anafthdev.calcc.data.Calc
import com.anafthdev.calcc.data.CalcCAction
import com.anafthdev.calcc.foundation.extension.split
import com.anafthdev.calcc.foundation.window.LocalComponentSize
import com.anafthdev.calcc.ui.theme.fully_rounded

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcCButtons(
	isAdvancedButtonExpanded: Boolean,
	onUpdateExpression: (Calc) -> Unit
) {
	
	val componentSize = LocalComponentSize.current
	
	Column(
		modifier = Modifier.fillMaxWidth()
	) {
		com.anafthdev.calcc.data.CalcCButtons.buttons.split(4).second.forEachIndexed { i, list ->
			
			val buttonPadding by animateDpAsState(
				targetValue = if (isAdvancedButtonExpanded) 6.dp
				else 8.dp
			)
			
			val buttonSize by animateDpAsState(
				targetValue = if (isAdvancedButtonExpanded) componentSize.calcCButton.width
					.minus(componentSize.calcCButton.width.div(4))
				else componentSize.calcCButton.width
			)
			
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Center,
				modifier = Modifier
					.fillMaxWidth()
			) {
				list.forEachIndexed { j, calc ->
					
					val interactionSource = remember { MutableInteractionSource() }
					val updateExpression = { onUpdateExpression(calc!!) }
					
					CalcCButton(
						calc = calc!!,
						interactionSource = interactionSource,
						buttonPadding = buttonPadding,
						buttonSize = buttonSize,
						onClick = updateExpression,
						colors = CardDefaults.cardColors(
							containerColor = if ((i == 0) or (list.lastIndex == j)) CardDefaults.cardColors().containerColor(
								enabled = true
							).value else Color.Transparent
						),
						modifier = Modifier
							.weight(1f)
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalcCButton(
	calc: Calc,
	colors: CardColors,
	interactionSource: MutableInteractionSource,
	buttonPadding: Dp,
	buttonSize: Dp,
	modifier: Modifier = Modifier,
	onClick: () -> Unit
) {
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
			colors = colors,
			onClick = onClick,
			modifier = Modifier
				.padding(buttonPadding)
				.size(buttonSize)
		) {
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.fillMaxSize()
			) {
				if (calc == CalcCAction.Delete()) {
					Icon(
						painter = painterResource(id = R.drawable.ic_delete),
						contentDescription = null
					)
				} else {
					Text(
						text = calc.symbol,
						style = MaterialTheme.typography.titleLarge.copy(
							fontWeight = FontWeight.Bold
						)
					)
				}
			}
		}
	}
}

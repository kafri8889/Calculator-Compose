package com.anafthdev.calcc.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.anafthdev.calcc.R
import com.anafthdev.calcc.data.Calc
import com.anafthdev.calcc.data.CalcCAction
import com.anafthdev.calcc.data.CalcCButtons
import com.anafthdev.calcc.foundation.extension.split
import com.anafthdev.calcc.foundation.window.LocalComponentSize
import com.anafthdev.calcc.ui.main.component.CalcCAdvancedButton
import com.anafthdev.calcc.ui.main.component.CalcCButtons
import com.anafthdev.calcc.ui.theme.fully_rounded
import com.anafthdev.calcc.ui.theme.superscriptSpanStyle
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
	
	val backgroundColorScheme = MaterialTheme.colorScheme.background
	
	val config = LocalConfiguration.current
	
	val mainViewModel = hiltViewModel<MainViewModel>()
	
	val state by mainViewModel.state.collectAsState()
	
	val expression = state.expression
	val calculationResult = state.calculationResult
	val isInverse = state.isInverse
	val useDeg = state.useDeg
	
	val systemUiController = rememberSystemUiController()
	val expressionTextScrollState = rememberScrollState()
	
	var isAdvancedButtonExpanded by remember { mutableStateOf(false) }
	
	LaunchedEffect(expression) {
		expressionTextScrollState.animateScrollTo(0)
	}
	
	SideEffect {
		systemUiController.setSystemBarsColor(
			color = backgroundColorScheme
		)
	}
	
	Column(
		modifier = Modifier
			.fillMaxSize()
	) {
		Box(
			contentAlignment = Alignment.CenterEnd,
			modifier = Modifier
				.fillMaxWidth()
				.height(
					config.screenHeightDp
						.div(4)
						.dp
				)
		) {
			Box(
				modifier = Modifier
					.fillMaxHeight()
					.width(
						config.screenWidthDp
							.div(9)
							.dp
					)
					.background(
						brush = Brush.horizontalGradient(
							listOf(
								Color.Transparent,
								backgroundColorScheme
							)
						)
					)
					.zIndex(2f)
					.align(Alignment.CenterEnd)
			)
			
			Column(
				horizontalAlignment = Alignment.End
			) {
				Text(
					text = buildAnnotatedString {
						expression.forEach { c ->
							if (c == '#') {
								withStyle(
									superscriptSpanStyle.copy(
										fontSize = MaterialTheme.typography.bodyMedium.fontSize
									)
								) {
									append("2")
								}
							} else append(c)
						}
					},
					style = MaterialTheme.typography.headlineLarge,
					modifier = Modifier
						.horizontalScroll(
							state = expressionTextScrollState,
							reverseScrolling = true
						)
						.padding(
							horizontal = 24.dp
						)
				)
				
				Text(
					text = calculationResult,
					style = MaterialTheme.typography.headlineSmall.copy(
						color = Color.Gray
					),
					modifier = Modifier
						.padding(
							horizontal = 24.dp
						)
				)
			}
			
			Box(
				modifier = Modifier
					.fillMaxHeight()
					.width(
						config.screenWidthDp
							.div(9)
							.dp
					)
					.background(
						brush = Brush.horizontalGradient(
							listOf(
								backgroundColorScheme,
								Color.Transparent
							)
						)
					)
					.zIndex(2f)
					.align(Alignment.CenterStart)
			)
		}
		
		Spacer(
			modifier = Modifier
				.weight(1f)
		)
		
		Column(
			modifier = Modifier
				.background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.18f))
		) {
			IconButton(
				onClick = {
					isAdvancedButtonExpanded = !isAdvancedButtonExpanded
				},
				modifier = Modifier
					.padding(4.dp)
					.rotate(
						if (isAdvancedButtonExpanded) 180f else 360f
					)
					.align(Alignment.CenterHorizontally)
			) {
				Icon(
					imageVector = Icons.Rounded.ArrowDropDown,
					contentDescription = null
				)
			}
			
			AnimatedVisibility(
				visible = isAdvancedButtonExpanded,
				enter = expandVertically(),
				exit = shrinkVertically()
			) {
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.background(MaterialTheme.colorScheme.onSurfaceVariant)
						.padding(8.dp)
						.wrapContentHeight()
				) {
					CalcCAdvancedButton(
						isInverse = isInverse,
						onUpdateExpression = { calc ->
							updateExpression(mainViewModel, expression, calc)
						}
					)
				}
			}
			
			Spacer(
				modifier = Modifier
					.padding(4.dp)
			)
			
			CalcCButtons(
				isAdvancedButtonExpanded = isAdvancedButtonExpanded,
				onUpdateExpression = { calc ->
					updateExpression(mainViewModel, expression, calc)
				}
			)
			
			Spacer(
				modifier = Modifier
					.fillMaxWidth()
					.padding(8.dp)
			)
		}
	}
}

private fun updateExpression(
	mainViewModel: MainViewModel,
	exp: String,
	calc: Calc
) {
	mainViewModel.dispatch(
		MainAction.UpdateExpression(
			mainViewModel.getExpression(exp, calc)
		)
	)
}

package com.anafthdev.calcc.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.anafthdev.calcc.R
import com.anafthdev.calcc.data.Calc
import com.anafthdev.calcc.data.CalcCAction
import com.anafthdev.calcc.data.CalcCButtons
import com.anafthdev.calcc.foundation.extension.split
import com.anafthdev.calcc.foundation.window.LocalComponentSize
import com.anafthdev.calcc.ui.theme.fully_rounded
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
	
	val backgroundColorScheme = MaterialTheme.colorScheme.background
	
	val config = LocalConfiguration.current
	val componentSize = LocalComponentSize.current
	
	val mainViewModel = hiltViewModel<MainViewModel>()
	
	val state by mainViewModel.state.collectAsState()
	
	val expression = state.expression
	val calculationResult = state.calculationResult
	
	val systemUiController = rememberSystemUiController()
	val expressionTextScrollState = rememberScrollState()
	
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
						.div(3)
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
					text = expression,
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
		
		Column(
			modifier = Modifier
				.weight(1f)
				.background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.18f))
		) {
			Box(
				modifier = Modifier
					.padding(8.dp)
					.size(
						width = 32.dp,
						height = 4.dp
					)
					.clip(fully_rounded)
					.background(Color.Gray)
					.align(Alignment.CenterHorizontally)
			)
			
			Spacer(
				modifier = Modifier
					.weight(1f)
			)
			
			CalcCButtons.values.split(4).second.forEachIndexed { i, list ->
				
				val r1 = list[0]!!
				val r2 = list[1]!!
				val r3 = list[2]!!
				val r4 = list[3]!!
				
				val r1InteractionSource = remember { MutableInteractionSource() }
				val r2InteractionSource = remember { MutableInteractionSource() }
				val r3InteractionSource = remember { MutableInteractionSource() }
				val r4InteractionSource = remember { MutableInteractionSource() }
				
				val updateExpressionR1 = { updateExpression(mainViewModel, expression, r1) }
				val updateExpressionR2 = { updateExpression(mainViewModel, expression, r2) }
				val updateExpressionR3 = { updateExpression(mainViewModel, expression, r3) }
				val updateExpressionR4 = { updateExpression(mainViewModel, expression, r4) }
				
				Row(
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.Center,
					modifier = Modifier
						.fillMaxWidth()
				) {
					Box(
						contentAlignment = Alignment.Center,
						modifier = Modifier
							.weight(1f, fill = true)
							.clickable(
								enabled = true,
								indication = null,
								interactionSource = r1InteractionSource,
								onClick = updateExpressionR1
							)
					) {
						Card(
							shape = fully_rounded,
							interactionSource = r1InteractionSource,
							colors = CardDefaults.cardColors(
								containerColor = if (i == 0) CardDefaults.cardColors().containerColor(
									enabled = true
								).value else Color.Transparent
							),
							onClick = updateExpressionR1,
							modifier = Modifier
								.padding(8.dp)
								.size(componentSize.calcCButton.width)
						) {
							Box(
								contentAlignment = Alignment.Center,
								modifier = Modifier
									.fillMaxSize()
							) {
								Text(
									text = r1.symbol,
									style = MaterialTheme.typography.titleLarge.copy(
										fontWeight = FontWeight.Bold
									)
								)
							}
						}
					}
					
					Box(
						contentAlignment = Alignment.Center,
						modifier = Modifier
							.weight(1f, fill = true)
							.clickable(
								enabled = true,
								indication = null,
								interactionSource = r2InteractionSource,
								onClick = updateExpressionR2
							)
					) {
						Card(
							shape = fully_rounded,
							interactionSource = r2InteractionSource,
							colors = CardDefaults.cardColors(
								containerColor = if (i == 0) CardDefaults.cardColors().containerColor(
									enabled = true
								).value else Color.Transparent
							),
							onClick = updateExpressionR2,
							modifier = Modifier
								.padding(8.dp)
								.size(componentSize.calcCButton.width)
						) {
							Box(
								contentAlignment = Alignment.Center,
								modifier = Modifier
									.fillMaxSize()
							) {
								Text(
									text = r2.symbol,
									style = MaterialTheme.typography.titleLarge.copy(
										fontWeight = FontWeight.Bold
									)
								)
							}
						}
					}
					
					Box(
						contentAlignment = Alignment.Center,
						modifier = Modifier
							.weight(1f, fill = true)
							.clickable(
								enabled = true,
								indication = null,
								interactionSource = r3InteractionSource,
								onClick = updateExpressionR3
							)
					) {
						Card(
							shape = fully_rounded,
							interactionSource = r3InteractionSource,
							colors = CardDefaults.cardColors(
								containerColor = if (i == 0) CardDefaults.cardColors().containerColor(
									enabled = true
								).value else Color.Transparent
							),
							onClick = updateExpressionR3,
							modifier = Modifier
								.padding(8.dp)
								.size(componentSize.calcCButton.width)
						) {
							Box(
								contentAlignment = Alignment.Center,
								modifier = Modifier
									.fillMaxSize()
							) {
								if (r3 == CalcCAction.Delete()) {
									Icon(
										painter = painterResource(id = R.drawable.ic_delete),
										contentDescription = null
									)
								} else {
									Text(
										text = list[2]!!.symbol,
										style = MaterialTheme.typography.titleLarge.copy(
											fontWeight = FontWeight.Bold
										)
									)
								}
							}
						}
					}
					
					Box(
						contentAlignment = Alignment.Center,
						modifier = Modifier
							.weight(1f, fill = true)
							.clickable(
								enabled = true,
								indication = null,
								interactionSource = r4InteractionSource,
								onClick = updateExpressionR4
							)
					) {
						Card(
							shape = fully_rounded,
							interactionSource = r4InteractionSource,
							colors = CardDefaults.cardColors(),
							onClick = updateExpressionR4,
							modifier = Modifier
								.padding(8.dp)
								.size(componentSize.calcCButton.width)
						) {
							Box(
								contentAlignment = Alignment.Center,
								modifier = Modifier
									.fillMaxSize()
							) {
								Text(
									text = r4.symbol,
									style = MaterialTheme.typography.titleLarge.copy(
										fontWeight = FontWeight.Bold
									)
								)
							}
						}
					}
				}
			}
			
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

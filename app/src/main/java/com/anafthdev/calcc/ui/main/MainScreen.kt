package com.anafthdev.calcc.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anafthdev.calcc.R
import com.anafthdev.calcc.data.CalcCAction
import com.anafthdev.calcc.data.CalcCButtons
import com.anafthdev.calcc.foundation.extension.split
import com.anafthdev.calcc.ui.theme.fully_rounded

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
	
	val config = LocalConfiguration.current
	
	val mainViewModel = hiltViewModel<MainViewModel>()
	
	val state by mainViewModel.state.collectAsState()
	
	val expression = state.expression
	
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
			Text(
				text = expression
			)
		}
		
		Column(
			modifier = Modifier
				.weight(1f)
				.background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.18f))
		) {
			Spacer(
				modifier = Modifier
					.weight(1f)
			)
			
			CalcCButtons.values.split(4).second.forEachIndexed { i, list ->
				Row(
					modifier = Modifier
						.fillMaxWidth()
				) {
					Box(
						modifier = Modifier
							.weight(1f, fill = true),
					) {
						Card(
							shape = fully_rounded,
							colors = CardDefaults.cardColors(
								containerColor = if (i == 0) CardDefaults.cardColors().containerColor(
									enabled = true
								).value else Color.Transparent
							),
							onClick = {  },
							modifier = Modifier
								.padding(8.dp)
								.size(48.dp)
						) {
							Box(
								contentAlignment = Alignment.Center,
								modifier = Modifier
									.fillMaxSize()
							) {
								Text(
									text = list[0]!!.symbol,
									style = MaterialTheme.typography.bodyLarge.copy(
										fontWeight = FontWeight.Bold
									)
								)
							}
						}
					}
					
					Box(
						modifier = Modifier
							.weight(1f, fill = true),
					) {
						Card(
							shape = fully_rounded,
							colors = CardDefaults.cardColors(
								containerColor = if (i == 0) CardDefaults.cardColors().containerColor(
									enabled = true
								).value else Color.Transparent
							),
							onClick = {  },
							modifier = Modifier
								.padding(8.dp)
								.size(48.dp)
						) {
							Box(
								contentAlignment = Alignment.Center,
								modifier = Modifier
									.fillMaxSize()
							) {
								Text(
									text = list[1]!!.symbol,
									style = MaterialTheme.typography.bodyLarge.copy(
										fontWeight = FontWeight.Bold
									)
								)
							}
						}
					}
					
					Box(
						modifier = Modifier
							.weight(1f, fill = true),
					) {
						Card(
							shape = fully_rounded,
							colors = CardDefaults.cardColors(
								containerColor = if (i == 0) CardDefaults.cardColors().containerColor(
									enabled = true
								).value else Color.Transparent
							),
							onClick = {  },
							modifier = Modifier
								.padding(8.dp)
								.size(48.dp)
						) {
							Box(
								contentAlignment = Alignment.Center,
								modifier = Modifier
									.fillMaxSize()
							) {
								if (list[2]!! == CalcCAction.Delete()) {
									Icon(
										painter = painterResource(id = R.drawable.ic_delete),
										contentDescription = null
									)
								} else {
									Text(
										text = list[2]!!.symbol,
										style = MaterialTheme.typography.bodyLarge.copy(
											fontWeight = FontWeight.Bold
										)
									)
								}
							}
						}
					}
					
					Box(
						modifier = Modifier
							.weight(1f, fill = true),
					) {
						Card(
							shape = fully_rounded,
							colors = CardDefaults.cardColors(),
							onClick = {  },
							modifier = Modifier
								.padding(8.dp)
								.size(48.dp)
						) {
							Box(
								contentAlignment = Alignment.Center,
								modifier = Modifier
									.fillMaxSize()
							) {
								Text(
									text = list[3]!!.symbol,
									style = MaterialTheme.typography.bodyLarge.copy(
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

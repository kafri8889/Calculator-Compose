package com.anafthdev.calcc.ui.main

import androidx.lifecycle.viewModelScope
import com.anafthdev.calcc.data.Calc
import com.anafthdev.calcc.data.CalcCAction
import com.anafthdev.calcc.data.CalcCNumber
import com.anafthdev.calcc.data.CalcCOperation
import com.anafthdev.calcc.foundation.viewmodel.StatefulViewModel
import com.anafthdev.calcc.ui.main.environment.IMainEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	environment: IMainEnvironment
): StatefulViewModel<MainState, Unit, MainAction, IMainEnvironment>(
	MainState(),
	environment
) {
	
	init {
		viewModelScope.launch(environment.dispatcher) {
			environment.getExpression().collect { exp ->
				setState {
					copy(
						expression = exp
					)
				}
			}
		}
	}
	
	override fun dispatch(action: MainAction) {
		when (action) {
			is MainAction.UpdateExpression -> {
				viewModelScope.launch(environment.dispatcher) {
					environment.setExpression(action.expression)
				}
			}
		}
	}
	
	fun getExpression(exp: String, calc: Calc): String {
		return when (calc) {
			is CalcCNumber -> exp + calc.symbol
			is CalcCOperation -> exp + calc.symbol
			is CalcCAction -> exp + calc.symbol
			else -> exp
		}
	}
	
}
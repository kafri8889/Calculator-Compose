package com.anafthdev.calcc.ui.main

import androidx.lifecycle.viewModelScope
import com.anafthdev.calcc.data.*
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
				environment.calculate()
				setState {
					copy(
						expression = exp
					)
				}
			}
		}
		
		viewModelScope.launch(environment.dispatcher) {
			environment.getCalculationResult().collect { result ->
				setState {
					copy(
						calculationResult = result
					)
				}
			}
		}
		
		viewModelScope.launch(environment.dispatcher) {
			environment.getInverse().collect { isInverse ->
				setState {
					copy(
						isInverse = isInverse
					)
				}
			}
		}
		
		viewModelScope.launch(environment.dispatcher) {
			environment.getUseDeg().collect { useDeg ->
				setState {
					copy(
						useDeg = useDeg
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
			is CalcCOperation.Sin -> exp + "${calc.symbol}("
			is CalcCOperation.Cos -> exp + "${calc.symbol}("
			is CalcCOperation.Tan -> exp + "${calc.symbol}("
			is CalcCOperation.Log -> exp + "${calc.symbol}("
			is CalcCOperation.NaturalLogarithm -> exp + "${calc.symbol}("
			is CalcCOperation -> exp + calc.symbol
			is CalcCAction.Percent -> exp + calc.symbol
			is CalcCAction.Decimal -> exp + calc.symbol
			is CalcCAction.Clear -> ""
			is CalcCAction.Delete -> {
				if (exp.isNotBlank()) exp.substring(0, exp.length - 1)
				else ""
			}
			is CalcCAction.Calculate -> {
				viewModelScope.launch(environment.dispatcher) {
					environment.calculate()
				}
				
				return exp
			}
			is CalcCAdvancedButton.OpenParenthesis -> exp + calc.symbol
			is CalcCAdvancedButton.CloseParenthesis -> exp + calc.symbol
			is CalcCAdvancedButton.Inverse -> {
				viewModelScope.launch {
					environment.setInverse()
				}
				
				return exp
			}
			is CalcCAdvancedButton.Rad -> {
				viewModelScope.launch {
					environment.useRad()
				}
				
				return exp
			}
			is CalcCAdvancedButton.Deg -> {
				viewModelScope.launch {
					environment.useDeg()
				}
				
				return exp
			}
			else -> exp
		}
	}
	
}
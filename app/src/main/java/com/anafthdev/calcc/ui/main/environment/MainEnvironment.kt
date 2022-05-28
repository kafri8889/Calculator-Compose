package com.anafthdev.calcc.ui.main.environment

import com.anafthdev.calcc.data.Calc
import com.anafthdev.calcc.data.CalcCAction
import com.anafthdev.calcc.data.Operations
import com.anafthdev.calcc.foundation.di.DiName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.operator.Operator
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class MainEnvironment @Inject constructor(
	@Named(DiName.DISPATCHER_MAIN) override val dispatcher: CoroutineDispatcher
): IMainEnvironment {
	
	private val _expression = MutableStateFlow("")
	private val expression: StateFlow<String> = _expression
	
	private val _calculationResult = MutableStateFlow("")
	private val calculationResult: StateFlow<String> = _calculationResult
	
	private val percentOperator = object : Operator(
		"!|",
		1,
		true,
		Operator.PRECEDENCE_DIVISION
	) {
		override fun apply(vararg args: Double): Double {
			return args[0] / 100
		}
	}
	
	override suspend fun setExpression(exp: String) {
		_expression.emit(exp)
	}
	
	override fun getCalculationResult(): Flow<String> {
		return calculationResult
	}
	
	override fun getExpression(): Flow<String> {
		return expression
	}
	
	override suspend fun calculate() {
		_calculationResult.emit(
			try {
				ExpressionBuilder(parseExpression(expression.value))
					.operator(percentOperator)
					.build()
					.evaluate()
					.toString()
			} catch (e: Exception) {
				""
			}
		)
	}
	
	private fun parseExpression(exp: String): String {
		var result = exp
		Operations.values.forEach { pair ->
			result = result.replace(pair.first, pair.second)
		}
		
		return result
	}
	
}
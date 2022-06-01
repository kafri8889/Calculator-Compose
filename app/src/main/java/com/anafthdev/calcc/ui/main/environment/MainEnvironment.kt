package com.anafthdev.calcc.ui.main.environment

import android.util.Log
import com.anafthdev.calcc.data.Calc
import com.anafthdev.calcc.data.CalcCAction
import com.anafthdev.calcc.data.Operations
import com.anafthdev.calcc.foundation.di.DiName
import com.anafthdev.calcc.foundation.extension.find
import com.anafthdev.calcc.foundation.extension.indexAll
import com.anafthdev.calcc.foundation.extension.isInt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function
import net.objecthunter.exp4j.operator.Operator
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.log10
import kotlin.math.sqrt

class MainEnvironment @Inject constructor(
	@Named(DiName.DISPATCHER_MAIN) override val dispatcher: CoroutineDispatcher
): IMainEnvironment {
	
	private val _expression = MutableStateFlow("")
	private val expression: StateFlow<String> = _expression
	
	private val _calculationResult = MutableStateFlow("")
	private val calculationResult: StateFlow<String> = _calculationResult
	
	/**
	 * if true, use deg else rad
	 */
	private val _useDeg = MutableStateFlow(true)
	private val useDeg: StateFlow<Boolean> = _useDeg
	
	private val _isInverse = MutableStateFlow(false)
	private val isInverse: StateFlow<Boolean> = _isInverse
	
	private val percentOperator = object : Operator(
		"!|",
		1,
		true,
		PRECEDENCE_DIVISION
	) {
		override fun apply(vararg args: Double): Double {
			return args[0] / 100
		}
	}
	
	private val factorialOperator = object : Operator(
		"!",
		1,
		true,
		PRECEDENCE_MULTIPLICATION
	) {
		override fun apply(vararg args: Double): Double {
			return if (args[0].isInt()) factorial(args[0].toInt()).toDouble() else Double.NaN
		}
	}
	
	private val naturalLogarithmFunction = object : Function("ln", 1) {
		override fun apply(vararg args: Double): Double {
			return 2.303 * log10(args[0])
		}
	}
	
	override suspend fun setExpression(exp: String) {
		_expression.emit(exp)
	}
	
	override suspend fun useDeg() {
		_useDeg.emit(true)
	}
	
	override suspend fun useRad() {
		_useDeg.emit(false)
	}
	
	override suspend fun setInverse() {
		_isInverse.emit(!isInverse.value)
	}
	
	override fun getCalculationResult(): Flow<String> {
		return calculationResult
	}
	
	override fun getExpression(): Flow<String> {
		return expression
	}
	
	override fun getInverse(): Flow<Boolean> {
		return isInverse
	}
	
	override fun getUseDeg(): Flow<Boolean> {
		return useDeg
	}
	
	override suspend fun calculate() {
		val parsedExpression = parseExpression(expression.value)
		_calculationResult.emit(
			try {
				ExpressionBuilder(
					if (parsedExpression.contains("(") and !parsedExpression.contains(")")) "$parsedExpression)"
					else parsedExpression
				)
					.function(naturalLogarithmFunction)
					.operator(
						percentOperator,
						factorialOperator,
					)
					.build()
					.evaluate()
					.toString()
			} catch (e: Exception) {
				""
			}
		)
	}
	
	private fun parseSqrt(exp: String): String {
		return try {
			var result = exp
			val sqrtIndex = result.indexAll('√').map { it + 1 }
			val sqrtList = arrayListOf<String>()
			
			sqrtIndex.forEach { i ->
				var sqrtV = "sqrt("
				
				if (result[i] == '(') {
					var totalOpenBrackets = 0
					val closeBrackets = result.find(')', i, result.length)
					
					for (c in result.substring(i)) {
						if (c == '(') {
							totalOpenBrackets++
						} else if (c == ')') {
							break
						}
					}
					
					if (closeBrackets.isNotEmpty()) {
						sqrtV += "${result.substring(i, closeBrackets[totalOpenBrackets - 1] + 1)})"
					}
				} else {
					val subResult = result.substring(i)
					for ((iC, c) in subResult.withIndex()) {
						if (c.isDigit()) {
							sqrtV += c
							if (iC == subResult.length-1) sqrtV += ')'
						} else {
							sqrtV += ')'
							break
						}
					}
				}
				
				sqrtList.add(
					sqrtV
				)
			}
			
			sqrtList.forEach { s ->
				result = result.replace(
					oldValue = "√" + s.replace("sqrt(", "").let {
						it.substring(0, it.length - 1)
					},
					newValue = s
				)
			}
			
			return result
		} catch (e: Exception) {
			exp
		}
	}
	
	private fun parseExpression(exp: String): String {
		var result = exp
		Operations.values.forEach { pair ->
			result = result.replace(pair.first, pair.second)
		}
		
		return parseSqrt(result)
	}
	
	private fun factorial(n: Int): Long {
		if (n == 0) {
			return 1
		}
		
		return factorial(n - 1) * n
	}
	
}
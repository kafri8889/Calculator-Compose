package com.anafthdev.calcc.ui.main.environment

import android.util.Log
import com.anafthdev.calcc.data.Calc
import com.anafthdev.calcc.data.CalcCAction
import com.anafthdev.calcc.data.Operations
import com.anafthdev.calcc.data.Parser
import com.anafthdev.calcc.foundation.di.DiName
import com.anafthdev.calcc.foundation.extension.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function
import net.objecthunter.exp4j.operator.Operator
import timber.log.Timber
import java.lang.Exception
import java.lang.Math.toDegrees
import java.lang.Math.toRadians
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.*

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
	
	private val powTwoOperator = object : Operator(
		"#",
		1,
		true,
		PRECEDENCE_POWER
	) {
		override fun apply(vararg args: Double): Double {
			return args[0].pow(2.0)
		}
	}
	
	private val naturalLogarithmFunction = object : Function("ln", 1) {
		override fun apply(vararg args: Double): Double {
			return 2.303 * log10(args[0])
		}
	}
	
	private val invSinDegrees = object : Function("ASIN") {
		override fun apply(vararg args: Double): Double {
			return toDegrees(asin(args[0]))
		}
	}
	
	private val invCosDegrees = object : Function("ACOS") {
		override fun apply(vararg args: Double): Double {
			return toDegrees(acos(args[0]))
		}
	}
	
	private val invTanDegrees = object : Function("ATAN") {
		override fun apply(vararg args: Double): Double {
			return toDegrees(atan(args[0]))
		}
	}
	
	private val sinDegrees = object : Function("Sin") {
		override fun apply(vararg args: Double): Double {
			return sin(toRadians(args[0]))
		}
	}
	
	private val cosDegrees = object : Function("Cos") {
		override fun apply(vararg args: Double): Double {
			return cos(toRadians(args[0]))
		}
	}
	
	private val tanDegrees = object : Function("Tan") {
		override fun apply(vararg args: Double): Double {
			return tan(toRadians(args[0]))
		}
	}
	
	init {
		CoroutineScope(dispatcher).launch {
			useDeg.collect {
				calculate()
			}
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
		changeSymbol(useDeg.value)
		
		val parsedExpression = parseExpression(expression.value)
		_calculationResult.emit(
			try {
				ExpressionBuilder(
					if (parsedExpression.contains("(") and !parsedExpression.contains(")")) "$parsedExpression)"
					else parsedExpression
				)
					.functions(
						naturalLogarithmFunction,
						invSinDegrees,
						invCosDegrees,
						invTanDegrees,
						sinDegrees,
						cosDegrees,
						tanDegrees
					)
					.operator(
						percentOperator,
						factorialOperator,
						powTwoOperator
					)
					.build()
					.evaluate()
					.toString()
					.replace("NaN", "")
			} catch (e: Exception) {
				Timber.i("calculation error: ${e.message}")
				Timber.i("parsedExpression: $parsedExpression")
				""
			}
		)
	}
	
	private fun parseExpression(exp: String): String {
		var result = exp
		val sinCosTanDegrees = listOf(
			Operations.SIN_DEGREES.first,
			Operations.COS_DEGREES.first,
			Operations.TAN_DEGREES.first,
		)
		
		val invSinCosTanDegrees = listOf(
			Operations.INV_SIN_DEGREES.first,
			Operations.INV_COS_DEGREES.first,
			Operations.INV_TAN_DEGREES.first,
		)
		
		Operations.values.forEach { pair ->
			if (pair.first.equalsOr(sinCosTanDegrees)) {
				if (useDeg.value) {
					result = result.replace(pair.first, pair.second)
				}
			} else if (pair.first.equalsOr(invSinCosTanDegrees)) {
				if (useDeg.value) {
					Timber.i("calculate: $pair")
					result = result.replace(pair.first, pair.second)
				} else {
					val inv = when (pair.first) {
						Operations.INV_SIN_DEGREES.first -> Operations.INV_SIN
						Operations.INV_COS_DEGREES.first -> Operations.INV_COS
						Operations.INV_TAN_DEGREES.first -> Operations.INV_TAN
						else -> Operations.INV_SIN
					}
					
					result = result.replace(inv.first, inv.second)
				}
			} else {
				result = result.replace(pair.first, pair.second)
			}
			
			Timber.i("calculate res: $result")
		}
		
		return Parser.parseSqrt(result)
	}
	
	private fun changeSymbol(useDeg: Boolean) {
		var result = expression.value
		val symbolsRad = listOf(
			Operations.INV_SIN,
			Operations.INV_COS,
			Operations.INV_TAN
		)
		
		val symbolsDeg = listOf(
			Operations.INV_SIN_DEGREES,
			Operations.INV_COS_DEGREES,
			Operations.INV_TAN_DEGREES
		)
		
		symbolsRad.forEachIndexed { i, pair ->
			result = if (useDeg) {
				result.replace(pair.first, symbolsDeg[i].first)
			} else {
				result.replace(symbolsDeg[i].first, pair.first)
			}
		}
		
		_expression.tryEmit(result)
	}
	
	private fun factorial(n: Int): Long {
		if (n == 0) return 1
		
		return factorial(n - 1) * n
	}
	
}
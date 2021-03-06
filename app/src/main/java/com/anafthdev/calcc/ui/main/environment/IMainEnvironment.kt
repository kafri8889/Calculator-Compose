package com.anafthdev.calcc.ui.main.environment

import com.anafthdev.calcc.data.Calc
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface IMainEnvironment {
	
	val dispatcher: CoroutineDispatcher
	
	suspend fun calculate()
	
	suspend fun setExpression(exp: String)
	
	suspend fun useDeg()
	
	suspend fun useRad()
	
	suspend fun setInverse()
	
	fun getCalculationResult(): Flow<String>
	
	fun getExpression(): Flow<String>
	
	fun getInverse(): Flow<Boolean>
	
	fun getUseDeg(): Flow<Boolean>
	
}
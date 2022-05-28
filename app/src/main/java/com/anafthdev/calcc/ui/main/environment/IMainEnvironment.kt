package com.anafthdev.calcc.ui.main.environment

import com.anafthdev.calcc.data.Calc
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface IMainEnvironment {
	
	val dispatcher: CoroutineDispatcher
	
	suspend fun setExpression(exp: String)
	
	fun getExpression(): Flow<String>
	
}
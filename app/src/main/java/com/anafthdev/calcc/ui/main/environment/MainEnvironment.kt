package com.anafthdev.calcc.ui.main.environment

import com.anafthdev.calcc.foundation.di.DiName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Named

class MainEnvironment @Inject constructor(
	@Named(DiName.DISPATCHER_IO) override val dispatcher: CoroutineDispatcher
): IMainEnvironment {
	
	private val _expression = MutableStateFlow("")
	private val expression: StateFlow<String> = _expression

}
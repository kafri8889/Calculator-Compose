package com.anafthdev.calcc.foundation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * State: A type that describes the data your feature needs to perform its logic and render its UI.
 *      The state is persistence during feature lifetime.
 *
 * Effect: Similar to state but it is not persistence such as navigation, show toast, show snackbar.
 
 * Action: A type that represents all of the actions that cause the state of the application to
 *      change such as user actions, notifications, event sources and more.
 
 * Environment: A type that holds any dependencies the feature needs, such as
 *      API clients, analytics clients, etc to decouple between UI layer and Data layer.
 */
abstract class StatefulViewModel<STATE, EFFECT, ACTION, ENVIRONMENT>(
	initialState: STATE,
	protected val environment: ENVIRONMENT
): ViewModel() {
	
	private val _state = MutableStateFlow(initialState)
	val state: StateFlow<STATE> = _state.asStateFlow()
	
	private val _effect = Channel<EFFECT>(Channel.BUFFERED)
	val effect = _effect.receiveAsFlow()
	
	protected suspend fun setEffect(newEffect: EFFECT) {
		_effect.send(newEffect)
	}
	
	protected suspend fun setState(newState: STATE.() -> STATE) {
		_state.emit(stateValue().newState())
	}
	
	private fun stateValue(): STATE {
		return state.value
	}
	
	abstract fun dispatch(action: ACTION)

}
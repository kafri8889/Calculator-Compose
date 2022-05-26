package com.anafthdev.calcc.ui.main

import androidx.lifecycle.viewModelScope
import com.anafthdev.calcc.data.Calc
import com.anafthdev.calcc.foundation.viewmodel.StatefulViewModel
import com.anafthdev.calcc.ui.main.environment.IMainEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
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
	
	}
	
	override fun dispatch(action: MainAction) {
		when (action) {
			is MainAction.Calculate -> {
				viewModelScope.launch(environment.dispatcher) {
				
				}
			}
		}
	}
	
}
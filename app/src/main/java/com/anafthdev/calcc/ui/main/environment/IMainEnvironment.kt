package com.anafthdev.calcc.ui.main.environment

import kotlinx.coroutines.CoroutineDispatcher

interface IMainEnvironment {
	
	val dispatcher: CoroutineDispatcher
}
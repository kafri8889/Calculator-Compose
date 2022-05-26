package com.anafthdev.calcc.ui.main.di

import com.anafthdev.calcc.ui.main.environment.IMainEnvironment
import com.anafthdev.calcc.ui.main.environment.MainEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainModule {
	
	@Binds
	abstract fun provideEnvironment(
		environment: MainEnvironment
	): IMainEnvironment
}
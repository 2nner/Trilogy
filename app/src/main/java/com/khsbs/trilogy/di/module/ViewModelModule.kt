package com.khsbs.trilogy.di.module

import androidx.lifecycle.ViewModelProvider
import com.khsbs.trilogy.di.ViewModelFactoryForDI
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactoryForDI): ViewModelProvider.Factory
}
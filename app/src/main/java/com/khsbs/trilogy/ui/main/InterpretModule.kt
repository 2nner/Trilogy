package com.khsbs.trilogy.ui.main

import androidx.lifecycle.ViewModel
import com.khsbs.trilogy.di.ViewModelKey
import com.khsbs.trilogy.di.scope.FragmentScoped
import com.khsbs.trilogy.ui.history.HistoryFragment
import com.khsbs.trilogy.ui.history.HistoryViewModel
import com.khsbs.trilogy.ui.input.InputTextFragment
import com.khsbs.trilogy.ui.language.SelectLanguageFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class InterpretModule {
    @Binds
    @IntoMap
    @ViewModelKey(InterpretViewModel::class)
    internal abstract fun bindInterpretViewModel(viewModel: InterpretViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    internal abstract fun bindHistoryViewModel(viewModel: HistoryViewModel): ViewModel

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeInputTextFragment(): InputTextFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeSelectLanguageFragment(): SelectLanguageFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contributeHistoryFragment(): HistoryFragment
}
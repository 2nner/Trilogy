package com.khsbs.trilogy.di.module

import com.khsbs.trilogy.di.scope.ActivityScoped
import com.khsbs.trilogy.ui.main.InterpretActivity
import com.khsbs.trilogy.ui.main.InterpretModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [InterpretModule::class])
    internal abstract fun interpretActivity(): InterpretActivity
}
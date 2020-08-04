package com.khsbs.trilogy.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/*
 * ViewModelFactory 클래스를 DI할 케이스를 위하여 추가함.
 * 이를 사용하기 위해 이 클래스를 bind하는 모듈을 생성하여 Component에 삽입하여 사용할 수 있다.
 * https://stackoverflow.com/questions/46042989/kotlin-dagger-inject-map-for-viewmodel-factory
 * https://android.jlelse.eu/7-steps-to-implement-dagger-2-in-android-dabc16715a3a
 */
class ViewModelFactoryForDI @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class $modelClass")
        }
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
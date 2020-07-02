package com.khsbs.trilogy.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khsbs.trilogy.BuildConfig
import com.khsbs.trilogy.api.ApiRepository
import com.khsbs.trilogy.model.LanguageType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.StringBuilder

class InterpretViewModel : ViewModel() {
    val inputMessage = MutableLiveData<String>()
    val sourceLanguage = MutableLiveData(LanguageType.KOR)
    val targetLanguage = MutableLiveData(LanguageType.ENG)

    val resultPapago = MutableLiveData<String>()
    val resultKakaoi = MutableLiveData<String>()
    val resultGoogle = MutableLiveData<String>()

    private val disposable = CompositeDisposable()

    fun interpret() {
        disposable.add(
            ApiRepository.papagoService.getTranslatedResult(
                BuildConfig.NAVER_CLIENT_ID,
                BuildConfig.NAVER_CLIENT_SECRET,
                sourceLanguage.value!!.papagoCode,
                targetLanguage.value!!.papagoCode,
                inputMessage.value ?: ""
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    resultPapago.value = it.message.result.translatedText
                }, {
                    resultPapago.value = it.message
                })
        )
        disposable.add(
            ApiRepository.kakaoiService.getTranslatedResult(
                "KakaoAK " + BuildConfig.KAKAO_API_KEY,
                inputMessage.value ?: "",
                sourceLanguage.value!!.kakaoiCode,
                targetLanguage.value!!.kakaoiCode
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val builder = StringBuilder()
                it.translatedText.forEach { listBlock->
                    listBlock.forEach { str ->
                        builder.append(str)
                    }
                }
                resultKakaoi.value = builder.toString()
            }, {
                resultKakaoi.value = it.message
            })
        )
    }

    fun swapLanguage() {
        sourceLanguage.value = targetLanguage.value.also { targetLanguage.value = sourceLanguage.value }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}

class InterpretViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InterpretViewModel() as T
    }
}
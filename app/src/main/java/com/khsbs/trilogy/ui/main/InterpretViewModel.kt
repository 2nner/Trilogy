package com.khsbs.trilogy.ui.main

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.khsbs.trilogy.BaseApplication
import com.khsbs.trilogy.BuildConfig
import com.khsbs.trilogy.repository.entity.InterpretHistory
import com.khsbs.trilogy.repository.remote.ApiRepository
import com.khsbs.trilogy.repository.entity.LanguageType
import com.khsbs.trilogy.repository.local.AppDatabase
import com.khsbs.trilogy.ui.custom.SingleLiveEvent
import com.khsbs.trilogy.ui.history.HistoryRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.text.StringBuilder

class InterpretViewModel : ViewModel() {
    val inputMessage = MutableLiveData<String>()
    val sourceLanguage = MutableLiveData(LanguageType.KOR)
    val targetLanguage = MutableLiveData(LanguageType.ENG)

    val resultPapago = MutableLiveData<String>()
    val resultKakaoi = MutableLiveData<String>()
    val resultGoogle = MutableLiveData<String>()

    val dialogEvent = SingleLiveEvent<Any>()

    private val disposable = CompositeDisposable()
    private val interpretRepository = InterpretRepository()
    private val historyRepository = HistoryRepository(AppDatabase.getDatabase().historyDao())

    fun interpret() {
        disposable.add(
            interpretRepository.interpret(sourceLanguage.value!!, targetLanguage.value!!, inputMessage.value ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val builder = StringBuilder()

                    resultPapago.value = it.resultPapago!!.message.result.translatedText

                    it.resultKakaoi!!.translatedText.forEach { listBlock ->
                        listBlock.forEach { str ->
                            builder.append(str)
                        }
                    }
                    resultKakaoi.value = builder.toString()

                    builder.clear()

                    it.resultGoogleTrans!!.data.translations.forEach { str ->
                        builder.append(str.translatedText)
                    }
                    resultGoogle.value = builder.toString()

                    insert(InterpretHistory(
                        inputMessage.value!!,
                        sourceLanguage.value!!.displayName,
                        targetLanguage.value!!.displayName,
                        resultPapago.value!!,
                        resultKakaoi.value!!,
                        resultGoogle.value!!
                    ))
                }, {
                    Toast.makeText(BaseApplication.context, "서버와의 통신 도중 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
                })
        )
    }

    fun swapLanguage() {
        sourceLanguage.value = targetLanguage.value.also { targetLanguage.value = sourceLanguage.value }
    }

    fun setLanguage(languageType: LanguageType, targetPoint: String) {
        if (targetPoint == "source") {
            sourceLanguage.value = languageType
        }
        else if (targetPoint == "target") {
            targetLanguage.value = languageType
        }

        Timber.d(
            "source : ${sourceLanguage.value!!.displayName} & target : ${targetLanguage.value!!.displayName}"
        )

        dialogEvent.call()
    }

    fun insert(history: InterpretHistory) = viewModelScope.launch(Dispatchers.IO) {
        historyRepository.insert(history)
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
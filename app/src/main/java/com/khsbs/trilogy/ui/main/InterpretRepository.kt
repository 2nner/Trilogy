package com.khsbs.trilogy.ui.main

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khsbs.trilogy.BaseApplication
import com.khsbs.trilogy.BuildConfig
import com.khsbs.trilogy.repository.entity.*
import com.khsbs.trilogy.repository.local.AppDatabase
import com.khsbs.trilogy.repository.remote.ApiRepository
import com.khsbs.trilogy.ui.history.HistoryRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

class InterpretRepository {
    fun interpret(source: LanguageType, target: LanguageType, inputMessage: String): Single<InterpretResult> {
        return Single.zip(
            ApiRepository.papagoService.getTranslatedResult(
                BuildConfig.NAVER_CLIENT_ID,
                BuildConfig.NAVER_CLIENT_SECRET,
                source.papagoCode,
                target.papagoCode,
                inputMessage
            ).onErrorResumeNext {
                if (it.localizedMessage.isNullOrEmpty()) {
                    Single.just(Papago(Papago.Message(Papago.Message.Result("오류가 발생하여 번역되지 않았습니다."))))
                } else {
                    Single.just(Papago(Papago.Message(Papago.Message.Result(it.localizedMessage!!))))
                }
            },
            ApiRepository.kakaoiService.getTranslatedResult(
                "KakaoAK " + BuildConfig.KAKAO_API_KEY,
                inputMessage,
                source.kakaoiCode,
                target.kakaoiCode
            ).onErrorResumeNext {
                if (it.localizedMessage.isNullOrEmpty()) {
                    Single.just(Kakaoi(listOf(listOf("오류가 발생하여 번역되지 않았습니다."))))
                } else {
                    Single.just(Kakaoi(listOf(listOf(it.localizedMessage!!))))
                }
            },
            ApiRepository.googleService.getTranslateResult(
                BuildConfig.GOOGLE_CREDENTIAL_API_KEY,
                inputMessage,
                source.googleCode,
                target.googleCode
            ).onErrorResumeNext {
                if (it.localizedMessage.isNullOrEmpty()) {
                    Single.just(GoogleTrans(GoogleTrans.Data(listOf(GoogleTrans.Data.Translations("오류가 발생하여 번역되지 않았습니다.")))))
                } else {
                    Single.just(GoogleTrans(GoogleTrans.Data(listOf(GoogleTrans.Data.Translations(it.localizedMessage!!)))))
                }
            },
            Function3 { t1, t2, t3 ->
                InterpretResult(t1, t2, t3)
            }
        )
    }
}
package com.khsbs.trilogy.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiRepository {
    const val URL_PAPAGO = "https://openapi.naver.com"
    const val URL_KAKAOI = "https://kapi.kakao.com"
    const val URL_GOOGLE = "https://translation.googleapis.com"

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(10000, TimeUnit.MILLISECONDS)
        .build()

    private val moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    val papagoService: PapagoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(URL_PAPAGO)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(PapagoApiService::class.java)
    }

    val kakaoiService: KakaoiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(URL_KAKAOI)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(KakaoiApiService::class.java)
    }
}
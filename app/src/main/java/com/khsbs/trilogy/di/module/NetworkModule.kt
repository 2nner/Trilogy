package com.khsbs.trilogy.di.module

import com.khsbs.trilogy.repository.remote.ApiRepository
import com.khsbs.trilogy.repository.remote.GoogleApiService
import com.khsbs.trilogy.repository.remote.KakaoiApiService
import com.khsbs.trilogy.repository.remote.PapagoApiService

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    /*@Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiRepository.URL_PAPAGO)
            .addConverterFactory(MoshiConverterFactory.create(ApiRepository.moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(ApiRepository.client)
            .build()*/

    @Provides
    fun providePapagoService(): PapagoApiService = ApiRepository.papagoService

    @Provides
    fun provideKakaoiService(): KakaoiApiService = ApiRepository.kakaoiService

    @Provides
    fun provideGoogleService(): GoogleApiService = ApiRepository.googleService
}
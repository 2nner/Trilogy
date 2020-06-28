package com.khsbs.trilogy.api

import com.khsbs.trilogy.model.Kakaoi
import com.khsbs.trilogy.model.Papago
import io.reactivex.Single
import retrofit2.http.*

interface PapagoApiService {
    @FormUrlEncoded
    @POST("v1/papago/n2mt")
    fun getTranslatedResult(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") cliendSecret: String,
        @Field("source") source: String,
        @Field("target") target: String,
        @Field("text") text: String
    ) : Single<Papago>
}

interface KakaoiApiService {
    @GET("v1/translation/translate")
    fun getTranslatedResult(
        @Header("Authorization") appKey: String,
        @Query("query") query: String,
        @Query("src_lang") srcLang: String,
        @Query("target_lang") targetLang: String
    ) : Single<Kakaoi>
}

interface GoogleApiService {

}
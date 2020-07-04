package com.puzzlebench.yelp_aac.data.remote.retrofit

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.puzzlebench.yelp_aac.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object FactoryYelpApi {

        private val PRIVATE_API_KEY_ARG = "Authorization"
        private fun getOkHttpClientBuilder(): OkHttpClient.Builder {

            return OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor { chain ->
                val defaultRequest = chain.request()
                val headers = defaultRequest.headers().newBuilder()
                    .add(
                        PRIVATE_API_KEY_ARG,
                        BuildConfig.API_KEY_VALUE
                    ).build()
                val requestBuilder = defaultRequest.newBuilder().headers(headers)
                chain.proceed(requestBuilder.build())
            }
        }

        fun makeServiceYelpApiV3(): YelpApiV3 {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClientBuilder().build())
                .baseUrl(BuildConfig.YELP_BASE_URL)
                .build()
            return retrofit.create(YelpApiV3::class.java)
        }
    }
}

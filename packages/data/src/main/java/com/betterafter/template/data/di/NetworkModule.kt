package com.betterafter.template.data.di

import com.betterafter.template.data.payment.api.FakePaymentApi
import com.betterafter.template.data.payment.api.PaymentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // 실제 서버 연동 시 BuildConfig / local.properties로 교체하세요.
    private const val BASE_URL = "https://example.com/api/"

    /** true면 Fake API, false면 Retrofit 실서버 */
    private const val USE_FAKE_API = true

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePaymentApi(retrofit: Retrofit): PaymentApi =
        if (USE_FAKE_API) {
            FakePaymentApi()
        } else {
            retrofit.create(PaymentApi::class.java)
        }
}

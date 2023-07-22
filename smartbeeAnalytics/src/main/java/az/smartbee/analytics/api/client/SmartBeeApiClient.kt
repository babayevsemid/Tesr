package az.smartbee.analytics.api.client

import az.smartbee.analytics.BuildConfig
import az.smartbee.analytics.api.interceptors.AuthorizationInterceptor
import az.smartbee.analytics.api.services.AnalyticsApiService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal object SmartBeeApiClient {
    fun provideAnalyticsApi(): AnalyticsApiService {
        return getRetrofit(BuildConfig.MOBILE_APP_EVENTS_URL).create(AnalyticsApiService::class.java)
    }

    private fun getRetrofit(baseUrl: String): Retrofit {
        val client = OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(AuthorizationInterceptor())
            .build()

        val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}
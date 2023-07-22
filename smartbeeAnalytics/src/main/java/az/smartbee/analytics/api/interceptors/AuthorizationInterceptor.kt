package az.smartbee.analytics.api.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newRequest()
        return chain.proceed(request)
    }

    private fun Request.newRequest() =
        newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
}
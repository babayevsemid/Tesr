package az.smartbee.analytics.api.services

import az.smartbee.analytics.api.utils.Resource
import az.smartbee.analytics.models.request.AnalyticsEventRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

internal interface AnalyticsApiService {
    @POST("mobile_app_events")
    suspend fun sendEvent(
        @Header("Authorization") token: String,
        @Body request: AnalyticsEventRequest
    ): Resource<Unit>
}
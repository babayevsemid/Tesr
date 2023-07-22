package az.smartbee.analytics.repository

import az.smartbee.analytics.api.services.AnalyticsApiService
import az.smartbee.analytics.models.request.AnalyticsEventRequest

internal class AnalyticsRepo(private val api: AnalyticsApiService) : BaseRepo() {
    suspend fun sendEvent(token: String, request: AnalyticsEventRequest) = callApi {
       api.sendEvent(token = token, request = request)
    }
}
package az.smartbee.analytics.persistence

import az.smartbee.analytics.api.client.SmartBeeApiClient
import az.smartbee.analytics.models.request.AnalyticsEventRequest
import az.smartbee.analytics.repository.AnalyticsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SmartBeeAnalytics private constructor() {
    private var repo = AnalyticsRepo(SmartBeeApiClient.provideAnalyticsApi())
    private val scope = CoroutineScope(Dispatchers.IO)
    private var token = ""

    fun sendEvent(
        eventType: String,
        idHash: String,
        utmSource: String,
        utmCampaign: String,
        parameters: HashMap<String, String> = hashMapOf()
    ) {
        val request = AnalyticsEventRequest(
            eventType = eventType,
            idHash = idHash,
            utmSource = utmSource,
            utm_campaign = utmCampaign,
            parameters = parameters
        )

        scope.launch {
            repo.sendEvent(
                token = token,
                request = request
            )
        }
    }

    fun setToken(token: String) {
        this.token = token
    }

    companion object {
        private var instance: SmartBeeAnalytics? = null

        @JvmStatic
        @Synchronized
        fun instance(): SmartBeeAnalytics {
            if (instance == null)
                instance = SmartBeeAnalytics()

            return instance as SmartBeeAnalytics
        }
    }
}
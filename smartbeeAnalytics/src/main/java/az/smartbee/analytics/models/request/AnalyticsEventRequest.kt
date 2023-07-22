package az.smartbee.analytics.models.request

import com.google.gson.annotations.SerializedName

data class AnalyticsEventRequest(
    @SerializedName("event_type")
    val eventType: String,
    @SerializedName("id_hash")
    val idHash: String,
    @SerializedName("utm_source")
    val utmSource: String,
    @SerializedName("utm_campaign")
    val utm_campaign: String,
    @SerializedName("parameters")
    val parameters: HashMap<String, String>
)
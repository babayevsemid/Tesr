package com.samid.smartbeeanalytics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import az.smartbee.analytics.persistence.SmartBeeAnalytics
import com.samid.smartbeeanalytics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendEventBtn.setOnClickListener {
            sendEvent()
        }
    }

    private fun sendEvent() {
        SmartBeeAnalytics.instance()
            .sendEvent(
                eventType = "Semocan",
                idHash = "Talocan",
                utmSource = "Sulocan",
                utmCampaign = "Inocan",
                parameters = hashMapOf(
                    "location" to "Bodrum",
                    "next_station" to "Nabran"
                )
            )
    }
}
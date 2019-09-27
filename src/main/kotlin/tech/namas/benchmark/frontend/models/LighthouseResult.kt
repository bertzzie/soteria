package tech.namas.benchmark.frontend.models

import kotlinx.serialization.*

@Serializable
data class LighthouseResult(val userAgent: String,
                            val lighthouseVersion: String,
                            val fetchTime: String,
                            val requestedUrl: String,
                            val finalUrl: String,
                            val audits: Audits)

@Serializable
data class Audits(
    @SerialName("first-contentful-paint")
    val firstContentfulPaint: Audit,

    @SerialName("first-meaningful-paint")
    val firstMeaningfulPaint: Audit,

    @SerialName("speed-index")
    val speedIndex: Audit,

    @SerialName("estimated-input-latency")
    val estimatedInputLatency: Audit,

    @SerialName("time-to-first-byte")
    val timeToFirstByte: Audit,

    @SerialName("first-cpu-idle")
    val firstCpuIdle: Audit,

    @SerialName("interactive")
    val interactive: Audit
)

@Serializable
data class Audit(val id: String,
                 val title: String,
                 val description: String,
                 val score: Float,
                 val scoreDisplayMode: String,
                 val numericValue: Float,
                 val displayValue: String)
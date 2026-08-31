package com.tristan.gameboostervip.domain.model

data class NetworkStats(
    val pingMs: Long?,
    val jitterMs: Long?,
    val packetLossPercent: Float?,
    val downloadMbps: Float?,
    val uploadMbps: Float?,
    val networkType: NetworkType,
    val signalStrengthLevel: Int?, // 0..4 when the platform exposes it, else null
    val quality: NetworkQuality,
    val timestampMillis: Long
)

enum class NetworkType { WIFI, CELLULAR, ETHERNET, VPN, NONE, UNKNOWN }

data class NetworkIssue(
    val type: NetworkIssueType,
    val description: String
)

enum class NetworkIssueType {
    HIGH_LATENCY,
    HIGH_JITTER,
    PACKET_LOSS,
    CONNECTION_UNSTABLE,
    NO_CONNECTIVITY
}

data class NetworkTestHistoryEntry(
    val id: Long = 0L,
    val packageName: String?, // null = general test, not tied to a game profile
    val stats: NetworkStats
)

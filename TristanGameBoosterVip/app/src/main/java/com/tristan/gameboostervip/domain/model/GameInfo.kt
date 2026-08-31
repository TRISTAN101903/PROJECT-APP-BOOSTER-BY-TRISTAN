package com.tristan.gameboostervip.domain.model

/**
 * A game detected on-device via PackageManager, or manually added by the user.
 * [iconUri] points at a cached copy of the app icon (see IconCacheRepository) —
 * we never hold a Drawable/Bitmap in a domain model or ViewModel state, since
 * that would leak Context-derived resources across configuration changes.
 */
data class GameInfo(
    val packageName: String,
    val displayName: String,
    val iconUri: String?,
    val isFavorite: Boolean = false,
    val isManuallyAdded: Boolean = false,
    val lastPlayedMillis: Long? = null,
    val lastSessionDurationMillis: Long? = null
)

enum class PerformanceMode { BALANCED, PERFORMANCE, BATTERY_SAVER }

data class GameProfile(
    val packageName: String,
    val performanceMode: PerformanceMode = PerformanceMode.BALANCED,
    val preferredResolutionLabel: String? = null,
    val preferredRefreshRateHz: Float? = null,
    val sensitivityNotes: String = "",
    val createdAtMillis: Long,
    val updatedAtMillis: Long
)

data class GameSessionRecord(
    val id: Long = 0L,
    val packageName: String,
    val startedAtMillis: Long,
    val endedAtMillis: Long?,
    val avgLatencyMs: Long?,
    val avgTemperatureCelsius: Float?,
    val batteryUsedPercent: Int?,
    val performanceScore: Int?
)

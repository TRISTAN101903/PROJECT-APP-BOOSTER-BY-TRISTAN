package com.tristan.gameboostervip.domain.model

/**
 * Snapshot of device performance metrics.
 *
 * Every field is nullable by design: a null value means the metric could not
 * be read on this device/Android version, and the UI must render "N/A" or
 * "NOT AVAILABLE ON THIS DEVICE" rather than inventing a number.
 */
data class DevicePerformance(
    val cpuUsagePercent: Float?,
    val ramUsedMb: Long?,
    val ramTotalMb: Long?,
    val ramAvailableMb: Long?,
    val temperatureCelsius: Float?,
    val thermalStatus: ThermalStatus?,
    val batteryLevelPercent: Int?,
    val batteryTemperatureCelsius: Float?,
    val isCharging: Boolean?,
    val networkLatencyMs: Long?,
    val storageAvailableGb: Float?,
    val storageTotalGb: Float?,
    val timestampMillis: Long
) {
    val ramUsagePercent: Float?
        get() = if (ramUsedMb != null && ramTotalMb != null && ramTotalMb > 0) {
            (ramUsedMb.toFloat() / ramTotalMb.toFloat()) * 100f
        } else null
}

enum class ThermalStatus {
    COOL,
    NORMAL,
    WARM,
    HIGH,
    UNKNOWN;

    companion object {
        /** Maps Android's PowerManager thermal status constants (API 29+) to our scale. */
        fun fromAndroidThermalStatus(status: Int): ThermalStatus = when (status) {
            0 -> COOL          // THERMAL_STATUS_NONE
            1 -> NORMAL        // THERMAL_STATUS_LIGHT
            2 -> WARM          // THERMAL_STATUS_MODERATE
            3, 4, 5, 6 -> HIGH // SEVERE, CRITICAL, EMERGENCY, SHUTDOWN
            else -> UNKNOWN
        }
    }
}

enum class NetworkQuality { EXCELLENT, GOOD, FAIR, POOR, UNKNOWN }

enum class StorageHealth { GOOD, WARNING, CRITICAL, UNKNOWN }

enum class DiagnosticSeverity { INFO, WARNING, CRITICAL }

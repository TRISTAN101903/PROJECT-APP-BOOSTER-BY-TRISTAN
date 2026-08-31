package com.tristan.gameboostervip.core.capability

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.PowerManager
import androidx.core.content.getSystemService

/**
 * Central capability-detection surface (Master Prompt §27).
 *
 * Every "can we read X on this device" question funnels through here so the
 * rest of the app never inlines a `Build.VERSION.SDK_INT >=` check directly.
 * Every check is defensive: a missing system service or a SecurityException
 * on a locked-down OEM build degrades to `false`, never a crash.
 */
class DeviceCapabilities(private val appContext: Context) {

    /** PowerManager.getCurrentThermalStatus() requires API 29 (Q). */
    fun isThermalSupported(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) return false
        return try {
            appContext.getSystemService<PowerManager>() != null
        } catch (_: Throwable) {
            false
        }
    }

    /**
     * "Network stats" here means ConnectivityManager.NetworkCapabilities link-speed
     * hints, available since API 23 (M). Active latency/throughput testing (ping,
     * download probe) doesn't need a special capability — it just needs INTERNET.
     */
    fun isNetworkStatsSupported(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    /**
     * Android has no public, reliable per-app FPS counter API. We can only
     * derive frame timing via Choreographer.FrameCallback deltas, which is an
     * approximation of the render loop, not a true GPU-reported FPS. We treat
     * this as "supported" from API 24+ (when Choreographer callbacks became
     * consistently available) but the UI must still label it as limited.
     */
    fun isFrameTimingApproximationSupported(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

    /** Display.getSupportedRefreshRates / Display.Mode requires API 23; getMode() API 23. */
    fun isRefreshRateSupported(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    /** ActivityManager.MemoryInfo has been available since API 1; always true. */
    fun isMemoryInfoSupported(): Boolean = true

    /** BatteryManager EXTRA_TEMPERATURE via ACTION_BATTERY_CHANGED sticky broadcast; API 1. */
    fun isBatteryTemperatureSupported(): Boolean = true

    /** ApplicationInfo.category (used to identify installed games) requires API 26. */
    fun isAppCategorySupported(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    fun hasPackage(packageName: String): Boolean {
        return try {
            appContext.packageManager.getPackageInfo(packageName, 0)
            true
        } catch (_: PackageManager.NameNotFoundException) {
            false
        } catch (_: Throwable) {
            false
        }
    }
}

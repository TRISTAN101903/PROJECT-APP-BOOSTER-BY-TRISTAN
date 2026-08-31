package com.tristan.gameboostervip.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class TristanThemeMode { TRISTAN_DARK, AMOLED_BLACK, CYBER_MODE }

private val tristanDarkScheme = darkColorScheme(
    primary = NeonCyan,
    onPrimary = TristanBgDeep,
    secondary = NeonViolet,
    onSecondary = TextPrimary,
    tertiary = NeonMagenta,
    background = TristanBgDeep,
    onBackground = TextPrimary,
    surface = TristanBgPanel,
    onSurface = TextPrimary,
    surfaceVariant = TristanBgPanelElevated,
    onSurfaceVariant = TextSecondary,
    error = NeonRed,
    onError = TextPrimary,
    outline = GlassStroke
)

private val amoledScheme = tristanDarkScheme.copy(
    background = TristanBgAmoled,
    surface = Color(0xFF080808),
    surfaceVariant = Color(0xFF121212)
)

private val cyberModeScheme = tristanDarkScheme.copy(
    primary = NeonMagenta,
    secondary = NeonCyan,
    tertiary = NeonViolet
)

@Composable
fun TristanGameBoosterTheme(
    themeMode: TristanThemeMode = TristanThemeMode.TRISTAN_DARK,
    content: @Composable () -> Unit
) {
    // The app is dark-mode-first by design (Master Prompt §2). We intentionally
    // do not branch on isSystemInDarkTheme(): a light variant is out of scope
    // for a "futuristic gaming / cyber performance" surface. The call is kept
    // here (unused) as a documented, deliberate decision point rather than an
    // oversight, in case a future light-mode accessibility variant is added.
    @Suppress("UNUSED_EXPRESSION")
    isSystemInDarkTheme()

    val colorScheme = when (themeMode) {
        TristanThemeMode.TRISTAN_DARK -> tristanDarkScheme
        TristanThemeMode.AMOLED_BLACK -> amoledScheme
        TristanThemeMode.CYBER_MODE -> cyberModeScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TristanTypography,
        content = content
    )
}

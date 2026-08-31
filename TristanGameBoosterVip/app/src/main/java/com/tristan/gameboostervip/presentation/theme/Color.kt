package com.tristan.gameboostervip.presentation.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Base surfaces
val TristanBgDeep = Color(0xFF0A0E17)
val TristanBgPanel = Color(0xFF12182B)
val TristanBgPanelElevated = Color(0xFF171F38)
val TristanBgAmoled = Color(0xFF000000)

// Glass panel overlay tints
val GlassStroke = Color(0x33FFFFFF)
val GlassFillLight = Color(0x14FFFFFF)

// Neon accents
val NeonCyan = Color(0xFF00E5FF)
val NeonViolet = Color(0xFF7C4DFF)
val NeonMagenta = Color(0xFFFF2D95)
val NeonGreen = Color(0xFF00FFA3)
val NeonAmber = Color(0xFFFFB020)
val NeonRed = Color(0xFFFF4757)

// Text
val TextPrimary = Color(0xFFF5F7FF)
val TextSecondary = Color(0xFF8A93B8)
val TextDisabled = Color(0xFF4C5372)

// Status
val StatusOptimal = NeonGreen
val StatusOk = NeonCyan
val StatusAttention = NeonAmber
val StatusCritical = NeonRed
val StatusUnavailable = TextDisabled

// Gradients used across cards, headers, and the Boost button
val BrandGradient = Brush.linearGradient(listOf(NeonCyan, NeonViolet))
val BrandGradientReverse = Brush.linearGradient(listOf(NeonViolet, NeonMagenta))
val ScoreRingGradient = Brush.sweepGradient(listOf(NeonCyan, NeonViolet, NeonMagenta, NeonCyan))

fun panelBackgroundBrush(): Brush = Brush.verticalGradient(
    listOf(TristanBgPanelElevated, TristanBgPanel)
)
